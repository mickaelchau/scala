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

// Assignment name: Implement double integral
//
// This homework assignment accompanies section "Convergence".
// The test cases can be found in the file DoubleIntegralTestSuite.scala
// You should complete the function, replacing ??? with correct Scala
// code so that the tests pass.

object DoubleIntegral {
  def slowDoubleIntegral(f:(Double,Double)=>Double,
                         xmin:Double, ymin:Double, xmax:Double, ymax:Double,
                         test:(Double,Double)=>Boolean):Double = {

    def single(x:Double):Double = {
      integral(y => ???,???,???,???)  // a function of y uses right and left bounds on y
    }
    integral(single,???,???,???) // a function of x uses right and left bounds on x
  }

  def main(argv:Array[String]): Unit = {

  }
}
