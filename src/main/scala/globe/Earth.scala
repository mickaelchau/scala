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

case class City(name:String,loc:Location,population:Int)

object Earth {

  import scala.math._ // scala.Math is deprecated

  val earthRadius = 6371.0 // km
  val earthCircumference = Pi * earthRadius * 2
  val earthCirumThresh = earthCircumference / 8.0

  def deltaSigma(loc1: Location, loc2: Location): Double = {

    val phi1 = toRadians(loc1.lat)
    val phi2 = toRadians(loc2.lat)
    val lambda1 = toRadians(loc1.lon)
    val lambda2 = toRadians(loc2.lon)

    acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lambda1 - lambda2))
  }

  def geodesicDistance(loc1: Location, loc2: Location): Double = {
    earthRadius * deltaSigma(loc1, loc2)
  }

  val rho = earthRadius

  @scala.annotation.tailrec
  def forceLongitude(degrees: Double): Double = {
    // returns a newly calculated longitude congruent to the
    // given one, but which: -180.0 <= degrees < 180.0
    if (degrees < -180)
      forceLongitude(degrees + 360)
    else if (degrees >= 180)
      forceLongitude(degrees - 360)
    else
      degrees
  }
  def sqr(x: Double): Double = x * x

  def euclidianDistanceSqr(p1: (Double, Double, Double), p2: (Double, Double, Double)): Double = {
    val (x1, y1, z1) = p1
    val (x2, y2, z2) = p2
    sqr(x1 - x2) + sqr(y1 - y2) + sqr(z1 - z2)
  }

  def euclidianDistanceSqr(loc1: Location, loc2: Location): Double = {
    val Point3(x1, y1, z1) = Point3(loc1)
    val Point3(x2, y2, z2) = Point3(loc2)
    sqr(x2 - x1) + sqr(y2 - y1) + sqr(z2 - z1)
  }

  def bisectGeodesic(loc1: Location, loc2: Location): Location = {
    if (forceLongitude(loc1.lon) == forceLongitude(loc2.lon))
    // if the two locations form a vertical line on the globe,
    // then we can calculate the midpoint just by averaging
    // the latitudes
    justifyLocation(Location((loc1.lat + loc2.lat) / 2, loc1.lon))
    else {
      val Point3(x1, y1, z1) = Point3(loc1)
      val Point3(x2, y2, z2) = Point3(loc2)
      // compute the midpoint of the 3-d line between the two points
      //   on the surface of the earth.  This point is within the interior
      //   of the earth, below ground.
      val (xm, ym, zm) = ((x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2)
      val modulus = sqrt(xm * xm + ym * ym + zm * zm)
      // project (xm,ym,zm) back up to the surface of the earth.
      // this is guaranteed to be a point on the geodesic connecting
      // loc1 and loc2.
      Location(Point3(xm / modulus * earthRadius,
                      ym / modulus * earthRadius,
                      zm / modulus * earthRadius))
    }
  }

  // returns angle swept by p0 -> x1 -> x2 in degrees
  // warning with this function you cannot distinguish between the
  // angles x and -x
  def measureAngle(p0: Location, x1: Location, x2: Location): Double = {
    // using spherical law of cosines
    val a = geodesicDistance(p0, x1) / earthRadius
    val b = geodesicDistance(x1, x2) / earthRadius
    val c = geodesicDistance(p0, x2) / earthRadius
    val quotient = (cos(c) - cos(a) * cos(b)) / (sin(a) * sin(b))
    acos(quotient) / Pi * 180
  }

  def intervalsOverlap(interval1: (Double, Double), interval2: (Double, Double)): Boolean = {
    // interval1 and interval2 are already each internally sorted west to east
    val (min1, max1) = interval1
    val (min2, max2) = interval2
    if (min1 > max1)
      intervalsOverlap((min1, 180), interval2) || intervalsOverlap((-180, max1), interval2)
    else if (min2 > max2)
      intervalsOverlap(interval1, (min2, 180)) || intervalsOverlap(interval1, (-180, max2))
    else
      min1.max(min2) <= max2.min(max1)
  }

  def justifyLocation(loc: Location): Location = {
    val Location(lat, lon) = loc

    if (lon < -180)
      justifyLocation(Location(lat, lon + 360))
    else if (lon >= 180)
      justifyLocation(Location(lat, lon - 360))
    else if (lat + 90 < 0)
      justifyLocation(Location(lat + 360, lon))
    else if (lat + 90 > 360)
      justifyLocation(Location(lat - 360, lon))
    else if (lat + 90 > 180)
      justifyLocation(Location(-lat - 180, lon + 180))
    else
      loc
  }

  def longitudeInterval(loc1: Location, loc2: Location): (Double, Double) = {
    // returns an interval from west to east
    val Location(_, lon1) = loc1
    val Location(_, lon2) = loc2
    val (minlon, maxlon) = if (lon1 <= lon2) (lon1, lon2) else (lon2, lon1)
    if (maxlon - minlon < 180)
      (minlon, maxlon)
    else
      (maxlon, minlon) // crosses 180-deg longitude line
  }

  type SEG = (Location, Location)

  def locationInsideTriangle(center: Location, triangle: List[Location]): Boolean = {
    require(triangle.length == 3)
    triangle.forall { p0: Location => {
      // u and v are the other to points in the triangle ignoring p0
      val u: Location = triangle.find(p => p != p0).get
      val v: Location = triangle.find(p => p != p0 && p != u).get
      geodesicDistance(center, p0) < min(geodesicDistance(p0, u), geodesicDistance(p0, v))
    }
    }
  }

  def removeQuotes(str:String) :String = {
    if (str == "")
      str
    else if (str(0) == '"')
      removeQuotes(str.drop(1))
    else if (str(str.size - 1 ) == '"')
      removeQuotes(str.dropRight(1))
    else
      str
  }

  def parseCities():Map[String,City] = {
    import java.io.InputStream
    import scala.io.Source

    val t: InputStream = getClass.getResourceAsStream("/simplemaps_worldcities_basic/worldcities.csv")
    // drop the first line of the file which looks like the following:
    //  "city","city_ascii","lat","lng","country","iso2","iso3","admin_name","capital","population","id"
    // lines look like the following
    //   "Abakan","Abakan","53.7037","91.4450","Russia","RU","RUS","Khakasiya","admin","167289","1643946203"
    // warning sometimes there is a , inside the city name, so we just skip those lines
    val cityLines: Iterator[String] = Source.createBufferedSource(t).getLines.drop(1)
    (for { line <- cityLines
           commas = line.count(_ == ',')
           quotes =  line.count(_ == '"')
           if quotes == (commas + 1)*2 // skip lines which have a comma within a quoted string
           parsed = line.split{","}
           city = removeQuotes(parsed(1).trim)
           lat = removeQuotes(parsed(2).trim).toDouble
           lon = removeQuotes(parsed(3).trim).toDouble
           populationStr = removeQuotes(parsed(9).trim)
           if populationStr != ""
           population = populationStr.toDouble.toInt
         }  yield city -> City(name=city,
                               loc=Location(lat,lon),
                               population=population)).toMap
  }
  val cities: Map[String, City] = parseCities()
}
