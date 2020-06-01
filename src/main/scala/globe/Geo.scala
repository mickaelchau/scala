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

object Geo {

  type Perimeter = List[Location]
  def buildBorders(epsilon:Double):Map[String,List[Perimeter]] = {
    import java.io.InputStream

    import scala.io.Source

    val t:InputStream = getClass.getResourceAsStream("/countries.geo.json")
    val jsonString: String = Source.createBufferedSource(t).getLines.fold("")(_ ++ _)
    buildBorders(jsonString,epsilon)
  }

  def buildBorders(jsonString:String,epsilon:Double):Map[String,List[Perimeter]] = {
    import io.circe._
    import io.circe.parser._
    val json = parse(jsonString).getOrElse(Json.Null)

    def filterClosePoints(points:Perimeter):List[Location] = {
      // filter out points that are too close
      // but leave the 1st and last point.
      // TODO if its a very small perimeter, we can remove it by returning None vs Option[List[Location]]
      //
      def recur(points:List[Location],done:Perimeter):Perimeter = {
        points match {
          case Nil => done.reverse
          case p1 :: Nil => (p1 :: done).reverse
          case p1 :: p2 :: tail => {
            val Location(y1, x1) = p1
            val Location(y2, x2) = p2
            if ((math.abs(x1 - x2) < epsilon) && (math.abs(y1 - y2) < epsilon))
              recur(p1 :: tail, done)
            else
              recur(p2 :: tail, p1 :: done)
          }
        }
      }
      recur(points,List())
    }

    def extractPolygons(polygons: List[List[List[Double]]]):List[Perimeter] = {
      polygons.map { linearRing =>
        val perimeter: Perimeter = linearRing.map { xy =>
          assert(xy.length == 2)
          // xy is longitude, latitude, buy Location(latitude,longitude)
          val long :: lat :: _ = xy
          Location(lat, long)
        }
        filterClosePoints(perimeter)
      }
    }

    case class Feature(name:String, perimeters:List[Perimeter])

    implicit val memberDecoder: Decoder[Feature] =
      (hCursor: HCursor) => {
        for {
          name <- hCursor.downField("properties").downField("name").as[String]
          geometryType <- hCursor.downField("geometry").downField("type").as[String]
          coords = hCursor.downField("geometry").downField("coordinates")
          perimeters <- geometryType match {
            case "Polygon" => coords.as[List[List[List[Double]]]].map(extractPolygons)
            case "MultiPolygon" => coords.as[List[List[List[List[Double]]]]].map(_.flatMap(extractPolygons))
            case _ => sys.error("not handled type=" + geometryType)
          }
        } yield Feature(name,perimeters)
      }

    val features: Option[Json] = json.hcursor.downField("features").focus

    features match {
      case None => sys.error("cannot find members in the json")
      case Some(features) => {
        val maybeFeatureList = features.hcursor.as[List[Feature]]
        maybeFeatureList match {
          case Right(features) => features.map{f:Feature => (f.name, f.perimeters)}.toMap
          case Left(error) => sys.error(error.getMessage)
        }
      }
    }
  }

  def main(argv:Array[String]):Unit = {
    val descrip = buildBorders("""{"type":"FeatureCollection",
                     "features":[
                                 {"type":"Feature","id":"AFG",
                                  "properties":{"name":"Afghanistan"},
                                  "geometry":{"type":"Polygon",
                                              "coordinates":[[[61.210817,35.650072],[62.230651,35.270664],[60.803193,34.404102],[61.210817,35.650072]]]}},
                                 {"type":"Feature","id":"AGO",
                                  "properties":{"name":"Angola"},
                                  "geometry":{"type":"MultiPolygon",
                                              "coordinates":[[[[16.326528,-5.87747],[16.57318,-6.622645]]],
                                                             [[[12.436688,-5.684304],[12.182337,-5.789931],[11.914963,-5.037987],[12.436688,-5.684304]]]]}},
                                 {"type":"Feature","id":"ALB",
                                  "properties":{"name":"Albania"},
                                  "geometry":{"type":"Polygon",
                                              "coordinates":[[[20.590247,41.855404],[20.463175,41.515089],[20.605182,41.086226],[20.590247,41.855404]]]}},
                                 {"type":"Feature","id":"ARE",
                                  "properties":{"name":"United Arab Emirates"},
                                  "geometry":{"type":"Polygon",
                                              "coordinates":[[[51.579519,24.245497],[51.757441,24.294073],[51.579519,24.245497]]]}}
                                ]}""",0.5)
    println(s"json=$descrip")
    ()
    }
}
