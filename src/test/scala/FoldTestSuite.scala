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

import homework.Fold._
import org.scalatest.FunSuite

class FoldTestSuite  extends FunSuite {
  test("running sum") {
    assert(List((1,1),(2,3),(3,6)) == runningSum(List(1,2,3)))
    assert(List() == runningSum(List()))
    assert(List((1,1)) == runningSum(List(1)))
    assert(List((1,1),(-1,0),(0,0),(1,1),(-1,0),(1,1),(-1,0)) == runningSum(List(1, -1, 0, 1, -1, 1, -1)))
  }

  test("harmonic sum"){
    assert(harmonicSum(List(1)) == 1.0)
    assert(harmonicSum(List(2)) == 1.0/2)
    assert(harmonicSum(List(1,2,4,8)) - (1.0/1 + 1.0/2 + 1.0/4 + 1.0/8) < .000001)
    assert(harmonicSum(List(1,-2,4,-8)) - (1.0/1 - 1.0/2 + 1.0/4 - 1.0/8) < .000001)
  }
  test("plus"){
    assert(plus((0.0,0.0),(0.0,0.0)) == (0.0,0.0))
    assert(plus((1.0,2.0),(3.0,4.0)) == (4.0,6.0))
  }
  test("times"){
    assert(times((1.0,0.0),(12.0,13.0)) == ( 12.0, 13.0))
    assert(times((0.0,1.0),( 0.0, 1.0)) == ( -1.0,  0.0))
    assert(times((3.0,5.0),(-7.0, 2.0)) == (-31.0,-29.0))
  }
  test("plus list"){
    assert((0.0,0.0) == plusList(Nil))
    assert((1.0,2.0) == plusList(List((1.0,2.0))))
    assert((-5.0,6.0) == plusList(List((1.0,0.0),(1.0,0.0),(3.0,2.0),(-10.0,4.0))))
  }
  test("times list"){
    assert((1.0,0.0) == timesList(Nil))
    assert((1.0,2.0) == timesList(List((1.0,2.0))))
    assert((-1.0,0.0) == timesList(List((0.0,1.0),(0.0,1.0))))
    assert((-38.0,-8.0) == timesList(List((1.0,0.0),(1.0,0.0),(3.0,2.0),(-10.0,4.0))))
  }
}
