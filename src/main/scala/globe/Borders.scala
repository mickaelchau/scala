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

import scala.annotation.tailrec

object Borders {
  // epsilon indicates the pixel size.  e.g., if epsilon=0.5
  //  then one pixel represents 0.5 degrees delta-latitude and 0.5 degrees delta-longitude
  def drawBorders(em:EarthMap,drawGeodesic:(Location,Location)=>Unit):Unit = {
    val borders = Geo.buildBorders(em.pixelDeltaDegree).toList.sortBy(_._1)
    for {
      (country, perimeters) <- borders
      perimeter <- perimeters
    } mapEdges(perimeter, drawGeodesic )
  }

  def locationsWestToEast(loc1:Location,loc2:Location):(Location,Location) = {
    // returns an interval from west to east
    //   if the west point is to the right of the east point, that means the segment
    //   crosses the 180 degree longitude line
    val Location(_,lon1) = loc1
    val Location(_,lon2) = loc2
    val (minlon,maxlon) = if (lon1 <= lon2) (loc1,loc2) else (loc2,loc1)
    if (maxlon.lon - minlon.lon < 180)
      (minlon,maxlon)
    else
      (maxlon,minlon)
  }

  @tailrec
  def mapEdges(perimeter: List[Location], f: (Location, Location) => Unit): Unit = {
    perimeter match {
      case p1 :: p2 :: tail => {
        f(p1, p2)
        mapEdges(p2::tail,f)
      }
      case _ => ()
    }
  }
}
