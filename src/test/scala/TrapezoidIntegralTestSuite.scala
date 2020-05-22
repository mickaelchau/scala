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

import homework.TrapezoidIntegral._
import lecture.Calculus.almostEqual
import org.scalatest.FunSuite

import scala.math._

class TrapezoidIntegralTestSuite extends FunSuite {
  test("faster double integral 1") {
    assert(0.0 == fasterDoubleIntegral((x, y) => 0.0,
                                       -1.0, -1.0, 1.0, 1.0,
                                       _ == _))
  }
  test("faster double integral 2") {
    assert(1.0 == fasterDoubleIntegral((x, y) => 1.0,
                                       0.0, 0.0, 1.0, 1.0,
                                       almostEqual(0.01)))
  }
  test("faster double integral 3") {
    // taken from http://www.stankova.net/statistics_2012/double_integration.pdf
    assert(almostEqual(0.01)(57.0, fasterDoubleIntegral((x: Double, y: Double) => 1 + 8 * x * y,
                                                        0.0, 1.0, 3.0, 2.0,
                                                        almostEqual(0.001))))
  }
  test("faster double integral 4") {
    assert(almostEqual(0.1)(1.0, fasterDoubleIntegral((x, y) => x * y,
                                                      0.0, 0.0, 1.0, 2.0,
                                                      almostEqual(0.001))))
  }
  test("faster double integral 5") {
    assert(almostEqual(0.01)(0.5, fasterDoubleIntegral((x, y) => y * sin(x),
                                                       0.0, 0.0, (Pi / 2), 1.0,
                                                       almostEqual(0.001))))
  }
  test("faster double integral 6") {
    // example from https://mathinsight.org/double_integral_examples
    assert(almostEqual(0.01)(2.0 / 3, fasterDoubleIntegral((x, y) => x * y * y,
                                                           0.0, 0.0, 2.0, 1.0,
                                                           almostEqual(0.0001))))
  }
}
