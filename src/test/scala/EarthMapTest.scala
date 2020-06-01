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

import globe._
import org.scalatest.FunSuite
import java.nio.file.Path
import java.nio.file.Files.createTempFile

class EarthMapTest extends FunSuite {
  test("earthMap 26") {
    new EarthMap().output(createTempFile("test-26-earthMap-",".png"))
  }

  test("EarthMap") {
    val em = new EarthMap(Location(-90, -180), Location(90, 180), width = 360, height = 180, borders = true)
    assert(em.locationToXy(Location(0, 0)) == Some((180, 90)))
    assert(em.locationToXy(Location(-89, 180)) == Some((0, 179)))
    assert(Earth.justifyLocation(Location(-91, 0)) == Location(-89, -180))
    assert(em.locationToXy(Location(-89, -180)) == Some((0, 179)))
    assert(em.locationToXy(Location(-91, 0)) == Some((0, 179)))
    assert(em.locationToXy(Location(89, -180)) == Some((0, 1)))
    assert(em.locationToXy(Location(91, 0)) == Some((0, 1)))
    assert(em.locationToXy(Location(0, 360)) == Some((180, 90)))
    assert(em.locationToXy(Location(0, -360)) == Some((180, 90)))
  }
  test("EarthMap 42 output") {
    val em = new EarthMap(Location(-90, -180), Location(90, 180), 360, 180, borders = true)
    em.output(createTempFile("test-42-earthMap-",".png"))
  }
  test("EarthMap 46 clipped output") {
    val em = new EarthMap(Location(0, 0), Location(90, 180), 360, 180, borders = true)
    em.output(createTempFile("test-46-earthMap-",".png"))
  }

  test("some cities 50"){
    val em = new PetersEarthMap()
    val cities = Earth.cities.take(50)
    for{ (name1,city1) <- cities
         (name2,city2) <- cities
         if name1 < name2}
      {
        em.drawLinePixels(city1.loc,city2.loc,Color(250,100,130))
        em.drawGeodesic(city1.loc,city2.loc,Color(100,100,230))
      }
    em.output(createTempFile("test-50-some-cities-peters-earth-map-",".png"))
  }
}
