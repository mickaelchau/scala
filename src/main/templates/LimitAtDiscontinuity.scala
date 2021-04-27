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

// Assignment name: Evaluate limit at a discontinuity
//
// This homework assignment accompanies section "Convergence".
// The test cases can be found in the file LimitTestSuite.scala
// You should complete the functions, replacing ??? with correct Scala
// code so that the tests pass.

object LimitAtDiscontinuity {
  def f(x:Double):Double = {
    // implementation of the polynomial

    //
    //    2
    //  x  + 2x - 3
    //  -------------
    //     x - 1
    //
    ???
  }

  def evalLimitAtDiscontinuity():Double = {
    limit(???, 0.1, ???)(???)
  }

}
