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

// The code in this package was initially provided by
//    Simon Parten - quafadas@gmail.com

package lecture

import viz.PlotTargets.desktopBrowser // tells us what to do with the spec
import viz.vega.plots._ // import all the plots...
import ujson.Value

object VegaPlot {
  def seriesScatterPlot(chartTitle: String,
                        xLabel:String,
                        yLabel:String,
                        data: Seq[(String, Seq[(Double, Double)])]):Any = {

    val polishData = for { (label, aSeq) <- data
                           point <- aSeq } yield ujson.Obj(
      "series" -> label,
      "x" -> point._1,
      "y" -> point._2
      )

    SeriesScatter(
      List(
        (spec: ujson.Value) => spec("title") = chartTitle, // set title
        (spec: Value) => spec("data") = ujson.Arr(ujson.Obj("name" -> "source",
                                                            "values" -> polishData)),
        (spec: Value) => spec("scales")(0)("zero") = false,
        (spec: Value) => spec("legends")(0)("title") = "Legend",
        (spec: Value) => spec("axes")(0)("title") = xLabel,
        (spec: Value) => spec("axes")(1)("title") = yLabel
        )
      )
  }
}

object TestVegaPlot {
  def main(argv: Array[String]): Unit = {
    VegaPlot.seriesScatterPlot("Test scatter plot",
                               xLabel = "Year",
                               yLabel = "Percentage",
                               Seq(("Fred", Seq((0, 10), (1, 20), (2, 14))),
                                   ("Mary", Seq((0, 15), (1, 22), (1, 24), (2, 12))),
                                   ("John", Seq((0, 14), (1, 25), (1, 23), (2, 17)))))
  }
}
