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

import homework.RunningAverage._
import lecture.Calculus.almostEqual
import org.scalatest.FunSuite

class RunningAverageTestSuite extends FunSuite {
  test("almost equal 2 ") {
    val calculated = runAverage(2, List(1.0, 2.0, 6.0, 4.0, 2.4, 3.5, 7.7))
    val control = List((1.0, 1.0), (2.0, 1.5), (6.0, 4.0), (4.0, 5.0), (2.4, 3.2), (3.5, 2.95), (7.7, 5.6))
    for((cal,con) <- calculated.zip(control)){
      val (a1,b1) = cal
      val (a2,b2) = con
      assert(a1 == a2)
      assert(almostEqual(0.001)(b1,b2))
    }
  }

  test("almost equal 3 ") {
    val calculated = runAverage(3,List(1.0,2.0,6.0,4.0,2.4,3.5,7.7))
    val control=List((1.0,1.0),(2.0,1.5),(6.0,3.0),(4.0,4.0),(2.4,4.133333333333334),
                     (3.5,3.3),(7.7,4.533333333333333))

    for((cal,con) <- calculated.zip(control)){
      val (a1,b1) = cal
      val (a2,b2) = con
      assert(a1 == a2)
      assert(almostEqual(0.001)(b1,b2))
    }
  }
  test("almost equal 4 ") {
    val calculated = runAverage(4,List(1.0,2.0,6.0,4.0,2.4,3.5,7.7))
    val control = List((1.0,1.0),(2.0,1.5),(6.0,3.0),(4.0,3.25),(2.4,3.6),(3.5,3.975),(7.7,4.4))

    for((cal,con) <- calculated.zip(control)){
      val (a1,b1) = cal
      val (a2,b2) = con
      assert(a1 == a2)
      assert(almostEqual(0.001)(b1,b2))
    }
  }
  test("almost equal 5 ") {
    val calculated = runAverage(5, List(1.0,2.0,6.0,4.0,2.4,3.5,7.7))
    val control = List((1.0,1.0), (2.0,1.5), (6.0,3.0), (4.0,3.25), (2.4,3.08), (3.5,3.58), (7.7,4.72))

    for((cal,con) <- calculated.zip(control)){
      val (a1,b1) = cal
      val (a2,b2) = con
      assert(a1 == a2)
      assert(almostEqual(0.001)(b1,b2))
    }
  }
}