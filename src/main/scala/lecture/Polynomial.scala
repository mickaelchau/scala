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

object Polynomial {
  import scala.math.{pow,sqrt}

  // We represent a polynomial such as 4x^3 - 2x +4
  //    as Map(3->4.0, 1->-1.0, 0->4.0)

  type POLY = Map[Int,Double]

  // given a polynomial and a numerical value, evaluate the polynomial.
  // E.g., evaluate(Map(2->2.2, 0-> -1.1),4.3))
  //       ==> 2.2*pow(4.3,2) - 1.1*pow(4.3,0)
  def evaluate(a:POLY,x:Double):Double = {
    a.foldLeft(0.0){case (acc,(e,c)) => acc + c * pow(x,e)}
  }

  // given two polynomials, add them to form a new polynomial
  // e.g., plus(Map(2->2.2, 0-> -1.1),Map(3->3.3, 0-> -1.1))
  //  ==> Map(3->3.3, 2->2.2, 0-> -2.2)
  def plus(a:POLY,b:POLY):POLY = {
    (a.keys ++ b.keys).map { e =>
      (a.get(e), b.get(e)) match {
        case (Some(c1), Some(c2)) => e -> (c1+c2)
        case (Some(c1), None) => e -> c1
        case (None, Some(c2)) => e -> c2
      }
    }.toMap
  }

  // detect whether two polynomials are close enough to qualify
  // as equal,  i.e., their RMS is less than epsilon
  def almostEqual(epsilon:Double)(a:POLY,b:POLY):Boolean = {
    // RMS mean of difference
    sqrt((a.keys ++ b.keys).foldLeft(0.0) { (acc, e) =>
      val diff = a.getOrElse(e, 0.0) - b.getOrElse(e, 0.0)
      diff * diff
    }) < epsilon
  }

  def main(argv:Array[String]):Unit = {
    ()
  }
}
