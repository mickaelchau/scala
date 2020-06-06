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

sealed abstract class Projection {
  // TODO
  // need to refactor Projection so that it projects onto the unit square
  // and invProject so that it projects from the unit square to the global location
  // EarthMap should then convert from unit square to the image canvas.
  // Also test to make sure that locationLL and locationUR work
  //   the same for different projections.

  // project is a function which takes a location on the globe, and reprojects it to
  //   a deformation of the glob.  The return value (lat,lon) of project is assumed to be
  //   interpretable as a location.  I.e., -90 <= lat <= 90 and -180 <= lon < 180
  def project(loc: Location):(Double,Double)

  // invProject is the inverse function of project
  def invProject(lat: Double, lon: Double):Location
}

object PetersProjection extends Projection {
  import scala.math._
  // peters projection
  def project(loc: Location): (Double, Double) = (90 * sin(toRadians(loc.lat)) -> loc.lon)
  def invProject(lat: Double, lon: Double): Location = Location(toDegrees(asin(lat / 90)), lon)
}

object SimpleProjection extends Projection {
  // simple projection
  def project(loc: Location):(Double,Double) = (loc.lat -> loc.lon)
  def invProject(lat: Double, lon: Double):Location = Location(lat,lon)
}

object Projection {
  val projections:Map[String,Projection] = Map("peters" -> PetersProjection
                                               ,"simple" -> SimpleProjection
                                               )
}