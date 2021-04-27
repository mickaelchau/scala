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

object RunningAverage {

  // runAverage returns a value indicating the running average of a give
  // list of numbers (Doubles).  The average of how many numbers is determined
  // by the given runLength.   E.g., if runLength==3, then we want to calculate
  // successive averages of 3 consecutive numbers.
  // The type of the return value is List[(Double,Double)], the first component
  // of each pair is the given number, i.e., the corresponding element of
  // samples.  The second component of each pair is the average of 3 (or runLength)
  // many elements of samples.
  // There is an exception for the first runLength - 1 elements.  In this case
  // you don't have runLength number of elements to average, so find the average
  // of all the elements so far.
  // Example, suppose samples = List(1.0,2.0,6.0,4.0,2.4)
  //          and     runLength = 3
  //   this function should return List((1.0, a1),(2.0, a2),(6.0, a3),(4.0,a4), (2.4,a5))
  //   where a1 = 1.0 / 1
  //         a2 = (1.0 + 2.0) / 2
  //         a3 = (1.0 + 2.0 + 6.0) / 3
  //         a4 = (2.0 + 6.0 + 4.0) / 3
  //         a5 = (6.0 + 4.0 + 2.4) / 3
  def runAverage(runLength:Int,samples:List[Double]):List[(Double,Double)] = {
    // history is List[(sample,average)]
    @tailrec
    def recur(samples: List[Double], history: List[(Double, Double)]): List[(Double, Double)] = {
      samples match {
        case Nil => history.reverse
        case s :: ss => {
          // declare more local variables if you wish
          val avg: Double = ???
          recur(???, (???, avg) :: history)
        }
      }
    }

    recur(???, ???)
  }

  def main(argv:Array[String]):Unit = {
    println(runAverage(2, List(1.0,2.0,6.0,4.0,2.4,3.5,7.7)))
    println(runAverage(3, List(1.0,2.0,6.0,4.0,2.4,3.5,7.7)))
    println(runAverage(4, List(1.0,2.0,6.0,4.0,2.4,3.5,7.7)))
    println(runAverage(5, List(1.0,2.0,6.0,4.0,2.4,3.5,7.7)))
  }
}
