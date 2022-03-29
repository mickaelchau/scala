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

package homework

import lecture.BabyNamePlot.getClass
import lecture.VegaPlot

import java.io.InputStream
import scala.annotation.tailrec
import scala.io.Source

// Assignment name: French baby names
//
// This homework assignment accompanies section "for comprehensions".
// The test cases can be found in the file FrenchNamesTestSuite.scala
// You should complete the function, replacing ??? with correct Scala
// code so that the tests pass.
object FrenchNames {

  // replace characters having diacritical marks with simple characters
  // E.g.,  'æ' => "ae"
  //        'â' | 'ä' | 'à' => "a"
  //        'è' | 'é' | 'ë' | 'ê' => "e"
  // etc for every special character in the file
  def elideString(name:String):String = {
    name.flatMap{ c =>
      if (c.toInt >= 'a' && c.toInt <= 'z'
          || c.toInt >= 'A' && c.toInt <= 'Z'
          || c == '-'
          || c == '\'')
        c.toString
      else
        ???
    }
  }

  // this function returns a Map which maps year to count, where count is
  // the number of babies named nameTarget in that year.  E.g., if
  // fred = babyNamesPerYear("fred","M"), then fred(2012)==100 means
  // there were 100 boy babies born named fred in 2012.
  def babyNamesPerYear(nameTarget: String, genderTarget: String): Map[Int,Int] = {
    ???
  }

  // Some French names can be spelled using different characters.
  // E.g., jerome vs jérôme vs jérome
  // This function has a Boolean flag indicating whether to elide different
  // spellings into one.  if elide=false, then "jerome" and "jérôme" are
  // considered different names, thus the Map returned might have keys
  // "jerome" and "jérôme".  If elide=true, then "jerome" and "jérôme"
  // are considered the same, in which case the Map returned will have
  // key "jerome" but not "jérôme".
  def babyNamesPerYear(nameTarget: String, genderTarget: String, elide:Boolean): Map[Int,Int] = {
    assert(nameTarget == nameTarget.toLowerCase,
           s"babyNamesPerYear must be called with lower case name, not $nameTarget")
    val s: InputStream = getClass.getResourceAsStream(s"/France-baby-names/nat2020.csv")
    val fp = Source.createBufferedSource(s)
    // remember to drop the first line of file: sexe;preusuel;annais;nombre
    // skip lines where name is _PRENOMS_RARES
    // skip lines where the year is XXXX
    // names are in caps (e.g., FABIENNE) but nameTarget will be given as lower case (e.g., fabienne)
    val pair_it = for {line <- ??? // HINT: fp.getLines().??? // drop the line sexe;preusuel;annais;nombre
                       Vector(gender_raw, name_raw, year_raw, count_raw ) = ???.toVector
                       // You will need to add several (5 to 10 at least) lines
                       // to transform gender_raw, name_raw, year_row, and count_raw
                       // into the exact data you'll need.  You'll will also need to
                       // include some conditionals (if ...) to filter away lines you
                       // want to thow away.
                       name = ???
                       year = ???
                       count = ???
                       } yield (name,year) -> count

    // you'll need to come up with some transform of pair_it
    // E.g., pair_it.toMap will create a Map[(String,Int),Int], but that won't be the exact
    //   type you need.  You need to compute and return a Map[Int,Int]
    ???
  }

  // count the number of babies born between yearMin and yearMax (including both yearMin and yearMax)
  // of the given gender and name.
  // the parameter elide, indicates (as above) whether to consider names
  //  such as "jerome" and "jérôme" as the same or different.
  //  If elide is true, then "jerome" and "jérôme" are considered the same.
  //  if elide is false, then "jerome" and "jérôme" are considered to be different names.
  def countNamesInYearRange(name:String, gender:String, yearMin:Int, yearMax:Int, elide:Boolean):Int = {
    val names = babyNamesPerYear(name, gender, elide)
    // now add up the counts of only the years you are interested in
    (yearMin to yearMax).foldLeft(???)((acc,year) => ???)
  }

  // Many French names are hyphenated such as "jean-albert" and "anne-marie".
  // Given a name such as "jean" return all other names X such that "jean-X" or "X-jean"
  // is a name registered in the resource data base.
  // We only care about exact names.  for example "jean-jerome" and "jean-jérôme" are different.
  // Do not attempt to elide names.
  // Careful!  If the given baseName never appears as some hyphenated form,
  //   then the empty Set should be returned.
  // Careful!  Some names have multiple hyphens:  e.g., abd-el-kader
  // Ignore any line for which year = XXXX.
  // WARNING some name might be hyphenated with itself.  E.g. jean-jean or marie-marie.
  //  Read the instructions carefully to understand what to do in this case.
  def hyphenatedNames(baseName:String):Set[String] = {
    assert(baseName == baseName.toLowerCase,
           s"hyphenatedNames must be called with lower case name, not $baseName")
    val s: InputStream = getClass.getResourceAsStream(s"/France-baby-names/nat2020.csv")
    val fp = Source.createBufferedSource(s)
    val it = for {line <- ??? // HINT: fp.getLines().??? // drop the line sexe;preusuel;annais;nombre
                  Vector(_, name_raw, year_raw, count_raw ) = ???.toVector
                  // You'll need to fill in several lines of converting *_raw
                  // and filtering, and string manipulation to find and correctly
                  // parse hyphenated names.
                  n <- ???
         } yield n
    it.toSet
  }

  def main(argv: Array[String]): Unit = {

  }
}
