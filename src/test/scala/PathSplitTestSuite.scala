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

import homework.PathSplit._
import lecture.Path._
import org.scalatest.funsuite.AnyFunSuite

class PathSplitTestSuite  extends AnyFunSuite {
  test("transfer at Place d'Italie") {
    // transfer at Place d'Italie
    val p1 = verticesToPath(List(260, 184, 352, 244, 243, 83, 128))
    val p2 = verticesToPath(List(260, 184, 352, 244))
    val p3 = verticesToPath(List(243, 83, 128))
    assert(splitPathForMetro(p1) == List(p2, p3))
  }
  test("leading with two Place d'Italie stations") {

    assert(splitPathForMetro(verticesToPath(List(244, 243, 83, 128)))
      == List(verticesToPath(List(243, 83, 128)))
           )
  }
  test("trailing with two Place d'Italie stations") {
    assert(splitPathForMetro(verticesToPath(List(260, 184, 352, 244, 243)))
             == List(verticesToPath(List(260, 184, 352, 244)))
           )
  }
  test("3 consecutive stations of Place d'Italie in the middle.") {
    assert(splitPathForMetro(verticesToPath(List(260, 184, 352, 244, 242, 243, 83, 128)))
             == List(verticesToPath(List(260, 184, 352, 244)),
                     verticesToPath(List(243, 83, 128)))
           )
  }
}
