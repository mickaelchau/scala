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

case class Point3(x: Double, y: Double, z: Double)

object Point3 {
  import math._  // scala.Math is deprecated
  def apply(loc: Location): Point3 = apply(loc.lat, loc.lon)

  // phi = latitude
  // theta = latitude
  // calculate 3d coords on the earth
  def apply(lat: Double, lon: Double): Point3 = {
    val phi = toRadians(lat)
    val theta = toRadians(lon)
    val cp = cos(phi)* Earth.earthRadius
    val x = cp * cos(theta) // cos(phi) * cos(theta)
    val y = cp * sin(theta) // cos(phi) * sin(theta)
    val z = sin(phi) * Earth.earthRadius
    Point3(x, y, z)
  }
}
