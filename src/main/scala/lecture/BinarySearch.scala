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

package lecture

import scala.annotation.tailrec

object BinarySearch {
  import scala.math._

  def binSearch(left:Double,
                right:Double,
                f:Double=>Double,
                target:Double,
                threshold:Double,
                maxDepth:Int
               ):Option[Double] = {
    // find where f(x) = target +/- threshold
    binSearch(left, right, x=>f(x)-target, threshold,maxDepth)
  }

  def binSearch(left:Double,
                right:Double,
                f:Double=>Double,
                threshold:Double ,
                maxDepth:Int
               ):Option[Double] = {
    // find where f(x) = 0.0 +/- epsilon
    val epsilon = abs(threshold * (f(left)-f(right)))
    @tailrec
    def recur(left:Double,
              right:Double,
              depth:Int
             ):Option[Double] = {
      val mid = (left + right)/2.0
      val fm = f(mid)

      if (depth >= maxDepth)
        None
      else if (abs(fm) < epsilon)
        Some(mid)
      else if (fm < 0)
        recur(mid,right,depth+1)
      else
        recur(left,mid,depth+1)
    }
    if ( f(left) <= 0.0 && f(right) >= 0.0)
      recur(left,right,0)
    else if (f(left) >= 0.0 && f(right) <= 0.0)
      recur(right,left,0)
    else
      None
  }

  def main(argv:Array[String]): Unit = {

  }
}
