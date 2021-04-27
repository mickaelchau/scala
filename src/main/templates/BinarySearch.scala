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

import scala.annotation.tailrec

// Assignment name: Binary search for Boolean function
//
// This homework assignment accompanies section "Binary Search".
// The test cases can be found in the file BinarySearchTestSuite.scala
// You should complete the function, replacing ??? with correct Scala
// code so that the tests pass.
object BinarySearch {

  def binSearchByBoolean(left:Double, right:Double, f:Double=>Boolean, delta:Double, maxDepth:Int):Option[Double] = {
    // takes a function, f, for which f(left) = false, and f(right) = true,
    // finds an x such that f(x)=false, and f(x+threshold)= true
    @tailrec
    def recur(left:Double,right:Double,depth:Int):Option[Double] = {
      val mid = ???

      if ( ??? )  // ??? < ???
        Some(???)
      else if (depth >= maxDepth)
        None
      else if ( f(mid))
        recur(???,???,???)
      else
        recur(???,???,???)

    }
    if ( !f(left) && f(right))
      recur(???,???,???)
    else
      ???
  }

  def main(argv:Array[String]):Unit = {

  }
}
