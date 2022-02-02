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

package lecture



import scala.annotation.tailrec

object FixedPoint {
  def fixedPoint[A](initial:A, f:A=>A, goodEnough:(A,A)=>Boolean):A = {
    @tailrec
    def improve(current:A):A = {
      val nextResult = f(current)
      if (goodEnough(current,nextResult))
        current
      else
        improve(nextResult)
    }
    improve(initial)
  }

  val maxIterations:Int = 1000
  def fixedPointM[A](initial:A, f:A=>A, goodEnough:(A,A)=>Boolean):A = {
    import cats._
    import cats.syntax.all._

    LazyList.from(1).foldM(initial) { (acc:A, n:Int) =>
      assert(n < maxIterations, "maximum iterations $maxIterations exceeded")
      val next: A = f(acc)
      if (goodEnough(acc, next))
        Left(next) // finished, return next
      else
        Right(next) // not yet finished, compute next iteration
    }.merge
  }

  def main(argv:Array[String]):Unit = {

  }
}
