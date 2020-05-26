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

package homework

object FoldTrig {
  def sumTuple(pair: (Double, Double)): Double = {
    val (m1, m2) = pair
    m1 + m2
  }

  // The number of terms of each series which are added.
  // You may need to increase this number to make the tests pass
  // the larger nTerms, the slower the computation, but the more
  // accurate the results. Suggestion, start with 5 and increase it until the
  // tests pass.
  val nTerms = ???

  //           1    x^1   x^2   x^3
  // exp(x) = --- + --- + --- + --- + ...
  //           0!    1!    2!    3!
  //  To add this series we take advantage of the fact that term T(n+1) = T(n) * (x/n).
  //  Thus we avoid explicitly calculating x^n and n!, because we always start with x^(n-1)/(n-1)!.
  //  The body of the foldLeft, does not simply calculate the sum, but rather calculates
  //  a pair (sum, nextTerm),  with nextTerm = term * x/n
  def exp(x: Double): Double = {
    sumTuple((1 to nTerms).foldLeft((0.0, 1.0)) {
      case ((sum, term), n) => (sum + term, (term * x/n))
    })
  }

  //           1    x^2   x^4   x^6
  // cos(x) = --- - --- + --- - --- + ...
  //           0!    2!    4!    6!
  // Follow the pattern of 'def exp' above to express the cosine computation
  // in terms of foldLeft.  The difference is that you need to
  //  1) make sure the calculated term alternates in sign from one iteration
  //       to the next
  //  2) the iterator feeding into foldLeft is not (1 to nTerms) but rather
  //       a different start and end point, and a step by 2
  //  3) given one term in the sum, what must you multiply it by
  //       to obtain the next?  It is no longer x/n as before.
  //  4) the iteration variable should traverse the even integers, (0, 2, 4 ...)
  def cos(x: Double): Double = {
    sumTuple((??? to ??? by 2).foldLeft((???, ???)) {
      case ((sum, term), n) => (sum + ???, term * ???)
    })
  }

  //          x^1   x^3   x^5   x^7
  // sin(x) = --- - --- + --- - --- + ...
  //           1!    3!    5!    7!
  // Follow the pattern of exp and cos above.   This can be done with the
  // same code inside the 2nd argument of foldLeft, but different first argument,
  // indicating the initial value of the sum.  Notice that the cos series
  // starts at 1, whereas the sin series starts at x.
  // Also be careful that the iteration variable must traverse the odd integers
  // rather than the even integers as before.
  def sin(x: Double): Double = {
    sumTuple((??? to ??? by ???).foldLeft((???, ???)) {
      case ((sum, term), n) => ???
    })
  }

}
