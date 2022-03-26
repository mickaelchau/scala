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

package lecture

import java.io.InputStream
import scala.io.Source



object BabyNamePlot {

  def babyNames(nameTarget: String, genderTarget: String, stateTarget: String): Unit = {
    //val lines = Source
    //  .fromFile(s"/Users/jnewton/Repos/data-set/US-baby-names/namesbystate/$stateTarget.TXT")
    //  .getLines()
    val s: InputStream = getClass.getResourceAsStream(s"/US-baby-names/namesbystate/$stateTarget.TXT")
    val fp = Source.createBufferedSource(s)
    val pairs = for {line <- fp.getLines()
                     Vector(state, gender, year_raw, name, count_raw) = line.split(",").toVector
                     if state == stateTarget
                     if gender == genderTarget
                     if name == nameTarget
                     } yield (year_raw.toDouble, count_raw.toDouble)

    VegaPlot.seriesScatterPlot(chartTitle="Baby Name data",
                               xLabel = "Year",
                               yLabel = "Total Names",
                               data = Seq((s"$nameTarget-$genderTarget-$stateTarget",
                                            pairs.toSeq)))
  }

  def main(argv: Array[String]): Unit = {
    babyNames("Mary", "F", "NY")
  }
}

object BabyNames2 {

  def babyNames(nameTarget: String, genderTarget: String, stateTarget: String):Seq[(Double,Double)] = {
    //val fp = Source.fromFile(s"/Users/jnewton/Repos/data-set/US-baby-names/namesbystate/$stateTarget.TXT")
    val s: InputStream = getClass.getResourceAsStream(s"/US-baby-names/namesbystate/$stateTarget.TXT")
    val fp = Source.createBufferedSource(s)
    val triples = for {line <- fp.getLines()
                       Vector(state, gender, year_raw, name, count_raw) = line.split(",").toVector
                       if state == stateTarget
                       if gender == genderTarget
                       } yield (year_raw.toInt, name, count_raw.toDouble)

    val data = for {
      (year,triples) <- triples.toList.groupBy(_._1) // group by year
      foundTriple <- triples.find(triple => nameTarget == triple._2)
      bornCount = triples.map(_._3).sum // add up the counts of each name
    } yield (year.toDouble, 100.0 * foundTriple._3 / bornCount)
    fp.close()

    data.toSeq
  }

  def plotBabyNames(chartTitle:String,
                    data:Seq[(String,String,String)]):Unit = {
    VegaPlot.seriesScatterPlot(chartTitle=chartTitle,
                               xLabel = "Year",
                               yLabel = "Percentage",
                               for{(nameTarget,genderTarget,stateTarget) <- data
                                   } yield  (s"$nameTarget-$genderTarget-$stateTarget",
                                 babyNames(nameTarget,genderTarget,stateTarget)))
    ()
  }

  def plotBabyNames(chartTitle:String, nameTarget: String, genderTarget: String, stateTarget: String):Unit = {
    VegaPlot.seriesScatterPlot(chartTitle=chartTitle,
                               xLabel = "Year",
                               yLabel = "Percentage",
                               data = Seq((s"$nameTarget-$genderTarget-$stateTarget",
                                            babyNames(nameTarget,genderTarget,stateTarget))))
    ()
  }

  def main(argv:Array[String]): Unit = {
    //    plotBabyNames("Baby Names", "Juan", "M", "MS")
    //    plotBabyNames("Baby Names", Seq(("Juan", "M", "MS"),
    //                                    ("Juan", "M", "NY"),
    //                                    ("Juan", "M", "CA")))
    plotBabyNames("Baby Names", Seq(("Blanche", "F", "MS"),
                                    ("Minnie", "F", "MS")))
    plotBabyNames("Baby Names", Seq(("John","M","NY")))
    
  }
}

