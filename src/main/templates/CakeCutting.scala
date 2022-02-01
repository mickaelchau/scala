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

object CakeCutting {
  // The numerical (cheating) solution of Mind Your Decisions
  // Can you Solve The Pythagoras Pie Puzzle?
  // https://www.youtube.com/watch?v=9PVcTdSlGBA&t=30s
  // In the video, the narrator solves the problem by finding
  // the closed form formula for the size of the n'th slice.
  // Your assignment is to do the calculation iteratively.

  def cmpPairs(p1: (Int, Double), p2: (Int, Double)): Boolean = {
    // return true if the Double in p1 is strictly greater than the Double in p2
    ???
  }

  def pieceSizesRec(): List[(Int, Double)] = {
    // This function, pieceSizesRec, uses tail recursion to perform the calculation.
    //
    // returns a list of (k,value) where value is the size of the kth piece of pie
    // the list runs from n ... 1
    // The returned list is sorted into order of decreasing slice size.
    def recur(n: Int, remainingSize: Double, acc: List[(Int, Double)]): List[(Int, Double)] = {
      ???
    }

    recur(???,???,???)
  }

  def pieceSizesFold(): List[(Int, Double)] = {
    // This function, pieceSizesFold, uses foldLeft to perform the calculation.
    //
    // returns a list of (k,value) where value is the size of the kth piece of pie
    // the list runs from n ... 1
    // The returned list is sorted into order of decreasing slice size.
    val z: (Double, List[(Int, Double)]) = (1.0, List())
    val partitioning = (1 to 100).foldLeft(???)(???)
    ??? // partitioning._2.sortWith(???)
  }

  def main(argv: Array[String]): Unit = {
    println("recursive: " + pieceSizesRec())
    println("folding: " + pieceSizesFold())
  }
}





