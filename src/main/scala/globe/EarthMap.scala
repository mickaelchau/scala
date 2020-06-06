// Copyright (c) 2020 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package globe

import java.nio.file.Path
import java.nio.file.Files.createTempFile
import scalafx.scene.image.WritableImage
import scala.math._

case class EarthMap(locationLL:Location=Location(-90,-180), // location on the globe in latitude;longitude
                    locationUR:Location=Location(90,180), // location on the globe in latitude;longitude
                    width:Int=360, height:Int=180, // size in pixels of the image file
                    borders:Boolean=true,
                    legend:Boolean=false,
                    grid:Option[(Double,Double)]=None,
                    projection:Projection = globe.SimpleProjection,
                    palette:ColorPalette = ColorPalette.defaultTemperatureColorPalette) {
  val Location(latMin: Double, lonMin: Double) = locationLL
  val Location(latMax: Double, lonMax: Double) = locationUR

  // degrees/pixel
  val pixelDeltaDegree: Double = 0.5 * (latMax - latMin) / width
  val colorBG: Color = Color(210, 210, 210)
  val colorLatLon: Color = Color(199,199,199)
  val colorBlack: Color = Color(0, 0, 0)
  private val wimg = new WritableImage(width, height)
  private val pw = wimg.pixelWriter

  def setColor(x: Int, y: Int, color: Color): Unit = {
    require(x < width, s"x=$x")
    require(x >= 0, s"x=$x")
    require(y < height, s"y=$y")
    require(y >= 0, s"y=$y")
    pw.setColor(x, y, scalafx.scene.paint.Color(color.red / 255.0,
                                                color.green / 255.0,
                                                color.blue / 255.0,
                                                1.0))
  }
  def drawPoint(loc: Location, measurement: Double): Unit = {
    drawPoint(loc, palette.chooseColor(measurement))
  }

  def drawPoint(loc: Location, color: Color): Unit = {
    // draw a point if it is within the range [(minLat,minLon),(maxLat,maxLon)]
    for {(x, y) <- locationToXy(loc)
         } setColor(x, y, color)
  }

  for {row <- 0 until height
       col <- 0 until width}
    setColor(col, row, colorBG)

  def mapLocations(client: Location => Unit): Unit = {
    for {y <- (0 until height).par
         x <- (0 until width)
         loc = xyToLocation((x, y))
         } client(loc)
  }

  def locationToXy(loc: Location): Option[(Int, Int)] = {
    val Location(lat, lon) = Earth.justifyLocation(loc)
    if (lon < lonMin || lon > lonMax || lat < latMin || lat > latMax)
      None
    else {
      val (latProj, lonProj) = projection.project(Location(lat,lon))
      val y = height - round(height * (latProj - latMin) / (latMax - latMin))
      val x = round((width - 1) * (lonProj - lonMin) / (lonMax - lonMin))
      Some((x.toInt.min(width - 1).max(0), y.toInt.min(height - 1).max(0)))
    }
  }

  def xyToLocation(xy: (Int, Int)): Location = {
    val (x, y) = xy
    val (lat,lon) = ((height - y) * (latMax - latMin) / height + latMin,
      x * (lonMax - lonMin) / width + lonMin)
    projection.invProject(lat,lon)
  }

  def drawGeodesic(loc1: Location, loc2: Location): Unit = {
    drawGeodesic(loc1, loc2, colorBlack)
  }

  def drawGeodesic(loc1: Location, loc2: Location, color: Color): Unit = {
    for {(x, y) <- calcGeodesicPixels(loc1, loc2)
         } setColor(x, y, color)
  }

  def calcGeodesicPixels(loc1: Location, loc2: Location): Set[(Int, Int)] = {
    def divideGeodesic(loc1: Location, loc2: Location): Set[(Int, Int)] = {
      val locm = Earth.bisectGeodesic(loc1, loc2)
      calcGeodesicPixels(loc1, locm) union calcGeodesicPixels(locm, loc2)
    }
    calcArcPixels(loc1,loc2,divideGeodesic)
  }

  def calcLatitudePixels(loc1: Location, loc2: Location): Set[(Int, Int)] = {
    require(loc1.lat == loc2.lat)
    def divideLatLine(loc1: Location, loc2: Location): Set[(Int, Int)] = {
      require(loc1.lat == loc2.lat)
      val locm = Location(loc1.lat, (loc1.lon + loc2.lon)/2)
      calcLatitudePixels(loc1,locm) union calcLatitudePixels(locm,loc2)
    }
    calcArcPixels(loc1,loc2,divideLatLine)
  }

  def calcArcPixels(loc1: Location,
                    loc2: Location,
                    divide:(Location,Location)=>Set[(Int,Int)]): Set[(Int, Int)] = {
    val epsilon = 0.0001 // km^2
    val xy1: Option[(Int, Int)] = locationToXy(loc1)
    val xy2: Option[(Int, Int)] = locationToXy(loc2)

    // if the two locations are close enough that they plot as the same pixel,
    //   then just return a Set of that pixel
    // otherwise, calculate a Location strictly between the the two locations
    //   on the geodesic.  Do this by calculating the midpoint of the segment
    //   connecting the two Locations which passes through the interior of the
    //   earth.  Find the midpoint of that segment, i.e. the vector from the
    //   center (0,0,0) to the point (xm,ym,zm).  Divide by its length and
    //   multiply by the radius of the earth to arrive at a point on the
    //   geodesic.  Convert this 3d point back to a Location.
    //   This point cuts the geodesic into two geodesics.  Recursively call
    //   calcGeodesicPixels and union the results.
    if (xy1.isEmpty && xy2.isEmpty)
      Set()
    else if (xy1 == xy2)
      Set(xy1.get)
    else if (round(loc1.lat) == 90 && round(loc2.lat) == 90)
      Set(xy1.get)
    else if (round(loc1.lat) == -90 && round(loc2.lat) == -90)
      Set(xy1.get)
    else if (xy1.nonEmpty && xy2.nonEmpty) {
      val dx = abs(xy1.get._1 - xy2.get._1)
      val dy = abs(xy1.get._2 - xy2.get._2)
      if ((dx <= 1 || dx == width - 1) && dy <= 1)
        Set(xy1.get, xy2.get)
      else
        divide(loc1, loc2)
    }
    else if (xy1.isEmpty && Earth.euclidianDistanceSqr(loc1, loc2) < epsilon)
      Set(xy2.get)
    else if (xy2.isEmpty && Earth.euclidianDistanceSqr(loc1, loc2) < epsilon)
      Set(xy1.get)
    else {
      divide(loc1, loc2)
    }
  }

  def pixelsTouch(xys:List[(Int,Int)]):Boolean = {
    val xysDistinct = xys.distinct
    import math.abs
    (xysDistinct.size == 1) ||
      xysDistinct.forall{case xy1@(x1,y1) => xysDistinct.exists{
        case xy2@(x2,y2) => (xy1 != xy2) && ((abs(x1-x2)<=1 || abs(x1-x2) == width-1) && abs(y1-y2)<=1)}}
  }

  def drawLinePixels(loc1:Location, loc2:Location, color:Color):Unit = {
    val Some((x1,y1)) = locationToXy(loc1)
    val Some((x2,y2)) = locationToXy(loc2)
    drawLinePixels(x1,y1,x2,y2,color)
  }

  def drawLinePixels(x1:Int,y1:Int,x2:Int,y2:Int,color:Color):Unit = {
    val dx = x2 - x1
    val dy = y2 - y1

    if (dx == 0) // vertical line
      for {y <- y2.min(y1) to y2.max(y1)}
      setColor(x1, y, color)
    else if (dy == 0) // horizontal line
        for {x <- x2.min(x1) to x2.max(x1)}
        setColor(x, y1, color)
      else if (abs(dx) > abs(dy))
      // less than 45 degree, so iterate over the x's
      // and calculate y as a function of x
      for {
        x <- x2.min(x1) to x2.max(x1)
        y = y1 + (x - x1) * dy / dx
      } setColor(x, y, color)
      else
      // more than 45 degree, so iterate over the y's
      // and calculate x as a function of y
      for {
        y <- y2.min(y1) to y2.max(y1)
        x = x1 + (y - y1) * dx / dy
      } setColor(x, y, color)
    ()
  }

  def output(filePath:Path):Boolean = {
    output(filePath.toString)
  }

  def output(fileName: String): Boolean = {
    output(fileName, "png")
  }

  def drawLegend():Unit = {
    val mMin = palette.palette.map{_._1}.min
    val mMax = palette.palette.map{_._1}.max
    for{ i <- (0 to 100)
         x <- (i/100.0 * width/5.0).toInt to ((i+1)/100.0 * width/5.0).toInt
         measure = if (palette.logScale == false )
           mMin + i/100*(mMax - mMin)
         else
           mMin * pow(pow(mMax / mMin,1.0/100),i)
         y <- (height - height/30 until height)
         } setColor(x,y,palette.chooseColor(measure))
  }

  def drawLongitudeLine(lon:Double) = {
      drawGeodesic(Location(0,lon),Location(90,lon),colorLatLon)
      drawGeodesic(Location(0,lon),Location(-90,lon),colorLatLon)
  }

  def drawLatitudeLine(lat:Double) = {
    for{ pixels <- List(calcLatitudePixels(Location(lat,-180),Location(lat,-60))
                        ,calcLatitudePixels(Location(lat,-60),Location(lat,60))
                        ,calcLatitudePixels(Location(lat,60),Location(lat,180)))
         (x,y) <- pixels
         } setColor(x, y, colorLatLon)
  }

  def drawGrid():Unit = {
    // draw latitude lines
    for {pair <- grid
         (dlat,_) = pair
         steps = (180 / dlat).round.toInt
         i <- 0 to steps
         lat = -90 + i*dlat
         } drawLatitudeLine(lat)
    // draw longitude lines
    for {pair <- grid
         (_,dlon) = pair
         steps = (360 / dlon).round.toInt
         i <- 0 to steps
         lon = -180 + i*dlon
         } drawLongitudeLine(lon)
  }
  def output(fileName: String, format: String): Boolean = {
    if (!fileName.endsWith("." + format))
      output( s"${fileName}.${format}", format)
    else {
      drawGrid()
      if (borders) {
        Borders.drawBorders(this, drawGeodesic(_, _, colorBlack))
      }
      if (legend) drawLegend()

      import javafx.embed.swing.SwingFXUtils._
      import javax.imageio.ImageIO.write
      val file = new java.io.File(fileName)
      println(s"generating $file")
      file.getParentFile().mkdirs()
      write(fromFXImage(wimg, null), "png", file)
    }
  }
}

object EarthMap {
  def colorizeCitiesByPopulation(): Unit = {
    locally {
      val em = EarthMap(borders=false,
                        legend=true,
                        projection=PetersProjection,
                        palette=ColorPalette.defaultPopulationColorPalette)

      for { (_,city) <- Earth.cities
            }  em.drawPoint(city.loc,
                            city.population.toDouble)

      val tmpFilePath: Path = createTempFile("test-population-earth-map-", ".png")
      em.output(tmpFilePath)
    }

    locally {
      val em = EarthMap(borders=true,
                        legend=true,
                        width=1000, height=600,
                        projection=PetersProjection,
                        palette=ColorPalette.defaultPopulationColorPalette)

      for { city <- Earth.cities.map{_._2}.toSeq.sortBy(_.population)
            }  em.drawPoint(city.loc,
                            city.population.toDouble)

      val tmpFilePath: Path = createTempFile("test-sorted-population-earth-map-", ".png")
      em.output(tmpFilePath)
    }
  }

  def drawLineAndGeodesic():Unit = {
    val em = EarthMap(legend=false
                      ,width=1000
                      ,height=600
                      ,grid=Some((15.0,20.0))
                      ,projection=SimpleProjection)

    em.drawLinePixels(Earth.cities("Abu Dhabi").loc, Earth.cities("Auckland").loc, Color(250, 100, 130))
    em.drawGeodesic(Earth.cities("Abu Dhabi").loc, Earth.cities("Auckland").loc, Color(100, 100, 230))
    val tmpFilePath: Path = createTempFile("test-main-earth-map-", ".png")
    em.output(tmpFilePath)
  }

  def main(argv: Array[String]): Unit = {
    drawLineAndGeodesic()
    colorizeCitiesByPopulation()
  }
}