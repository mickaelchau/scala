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

// lat = y
// lon = x
case class Location(lat: Double, lon: Double) {
  override def toString = f"[$lat%1.1f;$lon%1.1f]"
}

object Location {
  import math._ // scala.Math is deprecated
  import Earth.{earthRadius,justifyLocation}
  def apply(p:Point3):Location = {
    val Point3(x, y, z) = p
    val phid = toDegrees(asin(z / earthRadius)) // -90 to 90
    val thetad = toDegrees(atan2(y,x))
    //println(s"Location.apply: p=$p phid = $phid  thetad=$thetad")
    justifyLocation(Location(phid,thetad))
  }
}