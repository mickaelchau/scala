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

object Fold {

  // In a previous exercise you used fold to compute the sum of elements
  // of a list.  In this exercise you will look at more examples of fold and foldLeft

  // Compute the sum of reciprocals of the numbers in the given list
  // e.g. harmonicSum(List(1, 2, 4, 7)) =
  //    1.0/1 + 1.0/2 + 1.0/4 + 1.0/7
  // Recall that the sum of an empty list is 0,
  // the harmonic sum of an empty list is meaningless.
  // Therefore we will only consider non-empty lists.
  // Also it is guaranteed that no element of the input list is 0
  // so you can always compute 1.0/x
  // Hint, since data is not empty, you can always take its first element, data.head,
  //    but data.tail might be Nil.
  //    Thus the first argument of foldLeft may be some expression of data.head
  def harmonicSum(data:List[Int]):Double = {
    assert(data != Nil)
    ???
  }

  // runningSum takes a list, data, of integers such as List(2,4,6,8)
  // and returns a list of pairs (x,y) where x comes directly from data
  // and y is the sum of x and everything to the left of x in data.
  def runningSum(data:List[Int]):List[(Int,Int)] = {
    ???
  }

  // We wish to define an addition and multiplication on pairs of integers
  // according to the following rules
  // (a,b) plus (c,d) = (a+c,b+d)
  // (a,b) times (c,d) = (a*c-b*d, b*c+a*d)

  // define the functions plus and times
  // e.g., plus((1.0,10.0),(100.0,1000.0)) --> (101.0,1010.0)
  def plus(p1:(Double,Double),p2:(Double,Double)):(Double,Double) = {
    ???
  }
  // e.g., times((3.0,5.0),(-7.0,2.0)) == (-31.0,-29.0)
  def times(p1:(Double,Double),p2:(Double,Double)):(Double,Double) = {
    ???
  }

  // You may use your functions, plus and times, to compute the sum and product
  // of exactly two pairs at a time.
  // Use fold  to compute to generalize to a List of pairs of Double
  // You'll need to provide a so-called zero argument for fold in both
  // cases. In the first case the argument must be the identity of the
  // plus function, and in the second case, you'll need the identity
  // for the times function.   You need to figure out what these
  // elements are?  E.g., which pair (e1,e2) has the property that
  // for all (a,b), times((e1,e2),(a,b)) == (a,b) == times((a,b),(e1,e2)) ?
  def plusList(pairs:List[(Double,Double)]):(Double,Double) = {
    ???
  }
  def timesList(pairs:List[(Double,Double)]):(Double,Double) = {
    ???
  }
}
