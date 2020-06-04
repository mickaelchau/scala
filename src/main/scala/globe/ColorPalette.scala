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

import scala.math._ // scala.Math is deprecated

case class ColorPalette(colors:Iterable[(Double,Color)],
                        logScale:Boolean=false) {
  val palette: Seq[(Double, Color)] = colors.toList.sortBy {
    _._1
  }

  def chooseColor(measurement: Double): Color = {
    def loop(palette: List[(Double, Color)]): Color = {
      palette match {
        case (_, color) :: Nil => color
        case (v, color) :: _ if measurement <= v => color
        case (v1, Color(r1, g1, b1)) :: (v2, Color(r2, g2, b2)) :: _ if (v1 <= measurement) && (measurement <= v2) => {
          val scale = if (logScale)
            ((log(measurement) - log(v1)) / (log(v2) - log(v1))).toFloat
          else
            ((measurement - v1) / (v2 - v1)).toFloat

          def interpolate(lower: Int, upper: Int): Int = {
            max(0, min(255, lower + round((upper - lower) * scale)))
          }

          Color(interpolate(r1, r2), interpolate(g1, g2), interpolate(b1, b2))
        }
        case _ :: tail => loop(tail)
      }
    }

    loop(palette.toList)
  }
}

object ColorPalette {
  val defaultTemperatureColorPalette: ColorPalette = ColorPalette(Array(
    // celsius -> color
    (60.0, Color(255, 0, 0)),
    (15.0, Color(255, 255, 0)),
    (0.0, Color(255, 255, 255)),
    (-15.0, Color(0, 255, 255)),
    (-60.0, Color(0, 0, 255))))

  val defaultPopulationColorPalette: ColorPalette = ColorPalette(Array(
    // population -> Color
    (100.0, Color(0, 255, 255))
    ,(1e4, Color(0, 0, 255))
    ,(1e5, Color(0, 255, 0))
    ,(1e6, Color(255,255,0))
    ,(1e7, Color(255, 0, 0))
    //,(0.5e8, Color(255, 0, 0))
    ),
                                                                 logScale = true)
}