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

import org.scalatest._


class HistogramTestSuite extends FunSuite {
  import homework.Histogram
  test("construct"){
    Histogram(List(1,1,2,2,3,3,3))
    Histogram(Seq(1,1,2,2,3,3,3))
    Histogram(Array(1,1,2,2,3,3,3))
    Histogram(List(1.1,1.1,2.2,2.2,3.3,3.3,3.3))
    Histogram(Seq(1.1,1.1,2.2,2.2,3.3,3.3,3.3))
    Histogram(Array(1.1,1.1,2.2,2.2,3.3,3.3,3.3))
    Histogram(List("a","a","b","b"))
    Histogram(Seq("a","a","b","b"))
    Histogram(Array("a","a","b","b"))
  }
  test("from Set"){
    Histogram.fromSet(Set(1,2,3,4))
    Histogram.fromSet(Set("a", "b"))
  }
  test("append") {
    assert(Histogram(List(1,1,2,2,3,3,3)).data ==
                       (Histogram(List(1,2,3)) ++ Histogram(List(1,2,3,3))))
  }
  test("printing"){
    assert(s"${Histogram(List("a","b","b","a","b"))}"  == "[2 of a & 3 of b]")

    for{(h,str) <- List((Histogram(List("a","b","b","a","b")),
                          "[2 of a & 3 of b]")

                          ,( Histogram(List("a","b","b","a","b")) ++ Histogram(List("a","a","a")),
                          "[3 of b & 5 of a]")
                        ,(Histogram(List()),"[]")
                        )} assert(s"$h" == str)
  }

  test("frequency") {
    assert(Histogram(List("a","b","b","a","b")).frequency("a") == 2)
    assert(Histogram(List("a","b","b","a","b")).frequency("z") == 0)
    assert(Histogram(List("a","b","b","a","b")).frequency("b") == 3)
  }
  test("frequent") {
    assert(Histogram(List("a","b","b","a","b")).mostFrequent == Set("b"))
    assert(Histogram(List("a","b","b","a","b","c","a")).mostFrequent == Set("a", "b"))
    assert(Histogram(List("a","b","b","a","b","c","a")).leastFrequent == Set("c"))
    assert(Histogram(List()).leastFrequent == Set())
    assert(Histogram(List()).mostFrequent == Set())
  }

  test("same elements"){
    assert(Histogram(List(1,2,3,4)) sameElements Histogram(List(1,2,3,4)))
    assert(Histogram(List(1,2,3,4)) sameElements Histogram(Array(1,2,3,4)))
    assert(Histogram(List(1,2,3,4)) sameElements Histogram(Array(2,1,3,4)))
    assert(! (Histogram(List(1, 1,2,3,4)) sameElements Histogram(Array(2,1,3,4))))
    assert(! (Histogram(List(1, 1,2,3,4)) sameElements Histogram(Array(2,2,1,3,4))))
  }

  test("from set") {
    assert(Histogram(List(1,2,3)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(1,3,2)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(2,1,3)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(2,3,1)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(3,2,1)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(3,1,2)) sameElements Histogram.fromSet(Set(1,2,3)))
    assert(Histogram(List(1,2,3,4)) sameElements Histogram.fromSet(Set(4,1,2,3)))
    assert(Histogram(List()) sameElements Histogram.fromSet(Set()))
  }
}
