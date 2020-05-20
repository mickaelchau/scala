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

import homework.BinarySearch._
import lecture.BinarySearch._
import org.scalatest.FunSuite

import scala.math._

class BinarySearchTestSuite extends FunSuite {

  def almostEqual(tolerance:Double)(x:Double, y:Double):Boolean = {
    abs(x - y ) <= tolerance
  }

  test("tolerance") {
    assert(almostEqual(0.0)(1.0, 1.0))
    assert(almostEqual(0.5)(1.1, 1.2))
    assert(almostEqual(0.5)(100.1, 100.2))
    assert(almostEqual(0.1)(1000.01, 1000.05))
    assert(!almostEqual(0.01)(0.001, 0.101))
  }

  test("bin search") {

    assert(None != binSearch(-1.5, 1.0, sin, 0.0001, 20))
    assert(None != binSearch(-1.0, 1.0, sin, 0.000001, 30))

    // given function does not equal 0.0 in the given interval
    assert(None == binSearch(1.0, 2.0, (x: Double) => 3.0, 0.001, 20))

    assert(None != binSearch(-1.0, 1.0, sin, 0.1, 0.0001, 20))

    // interval reversed
    assert(None != binSearch(1.0, -1.0, sin, 0.1, 0.0001, 20))

    // given function does not equal 20.3 in the given interval
    assert(None == binSearch(1.0, -1.0, sin, 20.3, 0.001, 20))

    // not enough iterations allowed
    assert(None == binSearch(-0.9, 0.72, sin, 0.000001, 2))

  }

  test("boolean search") {

    def optionAlmostEqual(test: (Double, Double) => Boolean): (Option[Double], Option[Double]) => Boolean = {
      (op1, op2) => {
        (op1, op2) match {
          case (None,None) => true
          case (Some(a), Some(b)) => test(a, b)
          case _ => false
        }
      }
    }

    def ae(delta: Double): (Option[Double], Option[Double]) => Boolean = {
      optionAlmostEqual(almostEqual(delta))
    }

    // check for maxDepth exceeded
    assert(None == binSearchByBoolean(0.0, 100.0, _>= 5.0, 0.01,  3))
    assert(ae(0.1)(None,binSearchByBoolean(0.0, 100.0, _>= 5.0, 0.01,  2)))

    // check for successfully finding the switching point
    assert(ae(0.1)(Some(3.4), binSearchByBoolean(0.3, 52.1, _ > 3.4, 0.01, 1000)))

    // check for no such result in range
    assert(None == binSearchByBoolean(10.0, 20.0, _ > 3.4, 0.01, 100))
  }
}
