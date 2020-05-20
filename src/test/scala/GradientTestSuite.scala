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

import homework.Gradient._
import lecture.Calculus.almostEqual
import org.scalatest.FunSuite

import scala.math._

class GradientTestSuite extends FunSuite {
  def ae(z1:(Double,Double),z2:(Double,Double)):Boolean = {
    val (x1,y1) = z1
    val (x2,y2) = z2
    almostEqual(0.001)(x1, x2) && almostEqual(0.001)(y1,y2)
  }
  test("gradient"){
    assert((0.0,0.0) == gradient((x:Double,y:Double)=>0.0, 0.1, almostEqual(0.001), 3.0, 4.0))
    assert(ae((1.0,1.0),gradient((x:Double,y:Double)=>x+y, 0.1, almostEqual(0.001), 3.0, 4.0)))

    def f1(x:Double,y:Double) = x*x+y
    assert(ae((4.0,1.0), gradient((x:Double,y:Double)=>(x*x+y), 0.01, almostEqual(0.0001), 2.0, 0.0)))

    def f2(x:Double,y:Double):Double = x*x + y*y*y
    assert(ae((2.0,12.0), gradient(f2, 0.1, almostEqual(0.001), 1.0,2.0)))

    def f3(x:Double,y:Double):Double = x*cos(y) + y*sin(x)
    assert(ae((1.0,0.0),gradient(f3,0.1,almostEqual(0.0001),0.0,0.0)))
  }
}
