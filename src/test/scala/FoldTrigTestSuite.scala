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

import homework.FoldTrig.{cos, exp, sin}
import org.scalatest.FunSuite

import scala.math.{Pi, abs}

class FoldTrigTestSuite extends FunSuite {

  test("cos") {
    assert(math.abs(cos(0.0) - 1.0) < .0001)
    List(0.1, 0.2, -0.5, Pi / 2).foreach {
      case x => assert(abs(cos(x) - math.cos(x)) < 0.001)
    }
  }
  test("sin") {
    assert(math.abs(sin(0.0) - 0.0) < .0001)
    List(0.1, 0.2, -0.5, Pi / 2).foreach {
      case x => assert(abs(sin(x) - math.sin(x)) < 0.001)
    }
  }
  test("pythagoras") {
    List(0.1, 0.2, -0.5, Pi / 2).foreach {
      case x => {
        val s = sin(x)
        val c = cos(x)
        assert(abs(1.0 - (c * c + s * s)) < 0.0001)
      }
    }
  }
  test("exp") {
    assert(abs(math.exp(0.0) - 1.0) < 0.001)
    assert(abs(math.exp(1.0) - exp(1.0)) < 0.001)
    assert(abs(math.exp(2.0) - exp(2.0)) < 0.001)
    List(0.1, 0.2, -0.5, Pi / 2).foreach {
      case x => {
        // e^x * e^-x = e^0 = 1
        assert(abs(exp(x) * exp(-x) - 1.0) < 0.001)

        // exp(x)*exp(1-x) = e = exp(1)
        assert(abs(exp(x) * exp(1.0 - x) - exp(1.0)) < 0.001)

      }
    }
  }
}
