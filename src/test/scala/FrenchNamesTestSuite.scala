// Copyright (c) 2022 EPITA Research and Development Laboratory
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

import org.scalatest.funsuite.AnyFunSuite
import homework.FrenchNames._

class FrenchNamesTestSuite extends AnyFunSuite {

  test("prenoms rares"){
    assert(babyNamesPerYear("_prenoms_rares","M") == Map())
    assert(babyNamesPerYear("_prenoms_rares","F") == Map())
  }

  test("baby names per year") {
    val aaron = babyNamesPerYear("aaron","M")
    assert(aaron(2019) == 2443)
    assert(aaron(2018) == 2248)
    assert(aaron(2017) == 2383)
    assert(aaron(2000) == 118)

    val fabienne = babyNamesPerYear("fabienne","F")
    assert(fabienne(1999) == 23)
    assert(fabienne(1998) == 32)
    assert(fabienne(2010) == 7)
    assert(fabienne(2011) == 7)
    assert(fabienne(1962) == 4739)
  }

  test("count names in year range without elide"){
    assert(countNamesInYearRange("aaron", "M", 1900, 2000, elide=false)
           == 723)
    assert(countNamesInYearRange("fabienne", "M", 1900, 2000, elide=false)
           == 0)
    assert(countNamesInYearRange("fabienne", "F", 1900, 2000, elide=false)
           == 105177)
    assert(countNamesInYearRange("fabienne", "F", 1000, 3000, elide=false)
           == 105326)
    assert(countNamesInYearRange("fabienne", "F", 1962, 1962, elide=false)
           == 4739)
    assert(countNamesInYearRange("jeremy", "M", 1900, 2000, elide=false)
           == 106063)
    assert(countNamesInYearRange("jerémy", "M", 1900, 2000, elide=false)
           == 99)
    assert(countNamesInYearRange("agnes", "F", 1900, 2050, elide=false)
           == 158)
    assert(countNamesInYearRange("agnés", "F", 1900, 2050, elide=false)
           == 0)
    assert(countNamesInYearRange("agnès", "F", 1900, 2050, elide=false)
           == 112773)
  }

  test("count names in year range with elide"){

    assert(countNamesInYearRange("jeremy", "M", 1900, 2000, elide=true)
           == 121220)
    assert(countNamesInYearRange("jerome", "M", 1900, 2000, elide=true)
           == 206081)
    assert(countNamesInYearRange("zumra", "F", 2000, 2050, elide=true)
           == 287)
    assert(countNamesInYearRange("agnes", "F", 1900, 2050, elide=true)
           == 112931)
  }

  test("count names with elide"){
    // jerome vs jérôme vs jérome
    val j1 = babyNamesPerYear("jerome","M", elide=false)
    assert(j1(1997) == 525)
    assert(j1(1956) == 320)
    assert(j1(1939) == 73)

    val j2 = babyNamesPerYear("jérôme","M", elide=false)
    assert(j2(1932) == 3)
    assert(j2(1947) == 12)
    assert(j2(1984) == 763)
    assert(j2(1979) == 1174)

    val j3 = babyNamesPerYear("jérome","M", elide=false)
    assert(j3(1981) == 94)
    assert(j3(1972) == 87)
    assert(j3(2013) == 4)
    assert(j3(1966) == 29)
    assert(j3(1998) == 25)

    val j4 = babyNamesPerYear("jéröme","M", elide=false)
    assert(j4(1985) == 4)
    assert(j4(1975) == 4)
    assert(j4(1983) == 3)
    assert(j4(1974) == 3)
    assert(j4(1977) == 7)

    val j5 = babyNamesPerYear("jerôme","M", elide=false)
    assert(j5(1968) == 14)
    assert(j5(1964) == 13)
    assert(j5(1965) == 10)
    assert(j5(1997) == 11)

    val j6 = babyNamesPerYear("jerome","M", elide=true)

    assert(j6 != j1)

    assert(Map() == babyNamesPerYear("jérôme","M", elide=true))
    assert(Map() == babyNamesPerYear("jerôme","M", elide=true))
    assert(Map() == babyNamesPerYear("jérome","M", elide=true))
    assert(Map() == babyNamesPerYear("jéröme","M", elide=true))

    for(year <- 1900 to 2030){
      assert(j1.getOrElse(year,0)
             + j2.getOrElse(year,0)
             + j3.getOrElse(year,0)
             + j4.getOrElse(year,0)
             + j5.getOrElse(year,0) == j6.getOrElse(year,0),
             s"year=$year ${j1.getOrElse(year,0)} + ${j2.getOrElse(year,0)} + ${j3.getOrElse(year,0)} "
             + s"+ ${j4.getOrElse(year,0)} + ${j5.getOrElse(year,0)} != ${j6.getOrElse(year,0)}")
    }
  }

  test("hyphenated names"){
    assert(hyphenatedNames("jean").contains("jean"))
    assert(! hyphenatedNames("cyrille").contains("cyrille"))
    assert(hyphenatedNames("marie").contains("marie"))
    val jean = hyphenatedNames("jean")
    for{ n <- Seq("mario", "michael", "cyrille", "simon",
                  "sully", "cyril", "yannick", "lou", "mael",
                  "lucien", "eudes", "constant")
         } assert( jean.contains(n))
    assert(hyphenatedNames("catherine") == Set("sainte", "anne", "marie"))
    val el = hyphenatedNames("el")
    assert(el == Set("habib", "hadi", "amine", "kal", "hadji",
                     "krim", "nour", "rahman", "hadj", "houda", "mehdi",
                     "abd", "kader", "yamine", "mohamed"))
    val marie = hyphenatedNames("marie")
    for{ name <- el }{
      assert(hyphenatedNames(name).contains("el"))
    }
  }
}
