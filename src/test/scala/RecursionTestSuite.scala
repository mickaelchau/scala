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

import homework.Recursion._
import lecture.Recursion._
import org.scalatest.funsuite.AnyFunSuite

// This test suite accompanies the lecture section called "Recursion".
// The tests are intended to test the student's completion of
// homework assignment "Recursion exercise".

class RecursionTestSuite extends AnyFunSuite {
  test("simple recursion") {
    assert(0 == sumIntegersBySimpleRecursion(List()))
    assert(0 == sumIntegersBySimpleRecursion(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
    assert(15 == sumIntegersBySimpleRecursion(List(1, 2, 3, 4, 5)))
    assert(try {
      sumIntegersBySimpleRecursion(List.tabulate(100000)(_ => 0))
      false
    }
           catch {
             case e: StackOverflowError =>
               true
             case e =>
               assert(false, s"was not expecting $e")
               false
           })
  }

  test("tail recursion") {
    assert(0 == sumIntegersByTailRecursion(List()))
    assert(0 == sumIntegersByTailRecursion(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
    assert(15 == sumIntegersByTailRecursion(List(1, 2, 3, 4, 5)))
    assert(0 == sumIntegersByTailRecursion(List.tabulate(100000)(_ => 0)))
  }

  test("folding") {
    assert(0 == sumIntegersByFold(List()))
    assert(0 == sumIntegersByFold(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
    assert(15 == sumIntegersByFold(List(1, 2, 3, 4, 5)))
    assert(0 == sumIntegersByFold(List.tabulate(100000)(_ => 0)))
  }

  test("HW sum doubles") {

    assert(0.0 == sumDoublesBySimpleRecursion(List()))
    assert(0.0 == sumDoublesBySimpleRecursion(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))
    // we should actually have exact equality
    assert(16.5 == sumDoublesBySimpleRecursion(List(1.5, 2.0, 3.25, 4.0, 5.75)))

    assert(0.0 == sumDoublesByTailRecursion(List()))
    assert(0.0 == sumDoublesByTailRecursion(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))
    // we should actually have exact equality
    assert(16.5 == sumDoublesByTailRecursion(List(1.5, 2.0, 3.25, 4.0, 5.75)))

    assert(0.0 == sumDoublesByFold(List()))
    assert(0.0 == sumDoublesByFold(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))

    // we should actually have exact equality
    assert(16.5 == sumDoublesByFold(List(1.5, 2.0, 3.25, 4.0, 5.75)))

    assert(try {
      sumDoublesBySimpleRecursion(List.tabulate(100000)(_ => 0.0))
      false
    }
           catch {
             case e: StackOverflowError =>
               true
             case e =>
               assert(false, s"was not expecting $e")
               false
           })
    assert(0.0 == sumDoublesByFold(List.tabulate(100000)(_ => 0.0)))
  }

  test("HW product doubles") {

    assert(1.0 == productDoublesBySimpleRecursion(List()))
    assert(0.0 == productDoublesBySimpleRecursion(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))
    assert(1.0 == productDoublesBySimpleRecursion(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)))
    assert(0.0 == productDoublesBySimpleRecursion(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0)))
    // we should actually have exact equality
    assert(12.0 == productDoublesBySimpleRecursion(List(2.0, 3.0, 4.0, 0.5)))
    assert(try {
      productDoublesBySimpleRecursion(List.tabulate(100000)(_ => 0.0))
      false
    }
           catch {
             case e: StackOverflowError =>
               true
             case e =>
               assert(false, s"was not expecting $e")
               false
           })

    assert(1.0 == productDoublesByTailRecursion(List()))
    assert(0.0 == productDoublesByTailRecursion(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))
    assert(1.0 == productDoublesByTailRecursion(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)))
    assert(0.0 == productDoublesByTailRecursion(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0)))
    // we should actually have exact equality
    assert(12.0 == productDoublesByTailRecursion(List(2.0, 3.0, 4.0, 0.5)))
    assert(0.0 == productDoublesByTailRecursion(List.tabulate(10000)(_ => 0.0)))

    assert(1.0 == productDoublesByFold(List()))
    assert(0.0 == productDoublesByFold(List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)))
    assert(1.0 == productDoublesByFold(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)))
    assert(0.0 == productDoublesByFold(List(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0)))
    // we should actually have exact equality
    assert(12.0 == productDoublesByFold(List(2.0, 3.0, 4.0, 0.5)))
    assert(0.0 == productDoublesByFold(List.tabulate(100000)(_ => 0.0)))
  }

  test("My test") {
    def genLongList(len:Int, value:Double):List[Double] = {
      List.tabulate(len)(_ => value)
    }
    productDoublesBySimpleRecursion(genLongList(5864, 2.0))
  }
}