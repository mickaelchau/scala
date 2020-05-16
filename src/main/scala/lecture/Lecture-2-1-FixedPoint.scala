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

object FixedPoint {
  def fixedPoint[A](initial:A, f:A=>A, goodEnough:(A,A)=>Boolean):A = {
    def improve(current:A):A = {
      val nextResult = f(current)
      if (goodEnough(current,nextResult))
        current
      else
        improve(nextResult)
    }
    improve(initial)
  }

  def flatten(xs:List[Int]):List[Int] = {
    xs match {
      case List() => xs
      case head::Nil => xs
      case x1::x2::tail => if (x1 <= x2)
        x1 :: flatten(x2::tail)
      else
        flatten(x1::Math.abs(x2)+Math.abs(x1)::tail)
    }
  }

  def main(argv:Array[String]):Unit = {

    println(
      fixedPoint(List(-16, 0, -1, 7, 17, 5, -12, 16),
                 flatten,
                 (x:List[Int],y:List[Int])=> x==y
                 )
      )
  }


}
