// Copyright (c) 2020,21 EPITA Research and Development Laboratory
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

import lecture.Calculus._
import math._

object TrapezoidIntegral {

  def integralByTrapezoids(f:Double=>Double, left:Double, right:Double, test:(Double,Double)=>Boolean):Double = {
    // To complete this function, I suggest you follow the pattern
    // of the 'integral' function and 'sumRectangles' in Calculus.scala
    //
    def sumTrapezoids(partitionWidth:Double): Double = {
      // using foldLeft, calculate and return the sum of the areas
      // of as many trapezoids as possible each having a width (delta-x) of partitionWidth.
      // You must figure out how to compute the area of one trapezoid whose
      // bottom two corners lie on the x-axis, and whose top two corners
      // lie on the curve y=f(x) and whose width in the x direction is partitionWidth.
      ???
    }

    if (right == left)
      ??? // follow example from integral in Calculus.scala
    else if (right < left)
      -1 * integralByTrapezoids(???,???,???,???) // follow example from integral in Calculus.scala
    else
      limit(???,???,???)(???) // follow example from integral in  in Calculus.scala
  }

  def fasterDoubleIntegral(f:(Double,Double)=>Double,
                           xmin:Double, ymin:Double, xmax:Double, ymax:Double,
                           test:(Double,Double)=>Boolean):Double = {

    def single(x:Double):Double = {
      integralByTrapezoids(y => f(x,y),???,???,???) // follow example from slowDoubleIntegral which you implemented earlier
    }
    integralByTrapezoids(single,???,???,???) // follow example from slowDoubleIntegral which you implemented earlier
  }

  def main(argv:Array[String]) = {

  }
}
