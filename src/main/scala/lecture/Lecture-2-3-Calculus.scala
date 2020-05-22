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

package lecture
import scala.math._

object Calculus {

  def almostEqual(tolerance:Double)(x:Double, y:Double):Boolean = {
    abs(x - y ) <= tolerance
  }

  def limit(f: Double => Double, delta: Double, test: (Double, Double) => Boolean)(x: Double): Double = {
    @scala.annotation.tailrec
    def recur(h: Double, previous: Double): Double = {
      val improved = f(x + h)
      if (test(improved, previous)) {
        improved
      }
      else
        recur(h / 2, improved)
    }

    recur(delta / 2, f(x + delta))
  }

  def derivative(f: Double => Double, delta: Double, test: (Double, Double) => Boolean)(x: Double):Double = {

    def estimate(h: Double): Double = {
      (f(x + h) - f(x)) / h
    }
    //                      f(x+h) - f(x)
    // derivative = Limit   ------------- // which is a function of x
    //              h->0.0       h
    limit(estimate, delta, test)(0.0)
  }

  def integral(f:Double=>Double, left:Double, right:Double, test:(Double,Double)=>Boolean):Double = {

    def sumRectangles(partitionWidth: Double): Double = {
      val numPartitions = floor((right - left) / partitionWidth).toInt

      (0 until numPartitions).foldLeft(0.0) {
        (area, partition) => area + partitionWidth * f(left + partition * partitionWidth)
      }
    }
    if (right == left)
      0.0
    else if (right < left)
      -1 * integral(f, right, left, test)
    else
      limit(sumRectangles, (right - left) / 2, test)(0.0)
  }

  def main(argv:Array[String]):Unit = {
    println(almostEqual(0.001)(0, limit(cos,0.1,almostEqual(0.0001))(Pi/2)))

  }
}
