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

// You must complete the case class definition so that all the
//   tests pass.
// Use the file HistogramTestSuite to test your code.
// You may implement the internals of the class any way you like,
//   but without performing any mutating operation.

case class Histogram[A](items:Seq[A]) {
  val data: Map[A, Int] = items.groupBy(i => i).map{
    case (i,is) => i -> is.size
  }
  val byFreq = data.toList.groupBy(_._2).map{
    case (freq,pairs) => freq -> pairs.map{_._1}.toSet
  }
  val mostCommonFrequency:Option[Int] = byFreq.map{case (k,v) => k}.reduceOption(scala.math.max)
  val leastCommonFrequency:Option[Int] = byFreq.map{case (k,v) => k}.reduceOption(scala.math.min)

  // When a Histogram is printed, it is surrounded by square brackets [...]
  // Histogram(List("a")) ==> "[1 of a]"
  // Histogram(List("a","b","b")) ==> "[1 of a & 2 of b]"  ;; ordered by INCREASING frequency
  // Histogram(List("a","b","b","a")) ==> "[2 of a & 2 of b]"  ;; if the same frequency appears multiple times,
  //                                                            print in any order
  // Histogram(List("a","b","b","a","c")) ==> "[1 of c & 2 of a & 2 of b]"  ;; if the same frequency appears multiple times,
  // Between the brackets is 0 or more occurrences of the form x of y, where x is an integer ,
  //      and y is the printed representation of the object of type A
  override def toString = data.toArray.sortBy(_._2)
    .map{case (k,v) => s"$v of $k"}
    .mkString("[", " & ", "]")

  // is the set of given elements of this histogram the same as for
  //    another, that, histogram.  I.e., the same elements with the
  //    same frequencies, but the order might be different.
  def sameElements(that:Histogram[A]):Boolean = {
    data == that.data
  }

  // The set of elements occurring most often. This is a set of items, each of type A,
  //   all of which appear the same number of times, and no other element appears more often or as often.
  val mostFrequent:Set[A] = mostCommonFrequency.map{byFreq}.getOrElse(Set())

  // The set of elements occurring least often. This is a set of items, each of type A,
  //   all of which appear the same number of times, and no other element appears less often or as often.
  val leastFrequent:Set[A] = leastCommonFrequency.map{byFreq}.getOrElse(Set())

  // given an item, return an Int >= 0 designating how many times that
  //  item appears in the items
  def frequency(item:A):Int = {
    data.getOrElse(item,0)
  }

  // ++ appends a given Histogram with the original one
  //  Histogram(List("a","b")) ++ Histogram(List("a","c"))
  //
  def ++(that:Histogram[A]):Histogram[A] = Histogram(this.items ++ that.items)

}

object Histogram {
  def fromSet[A](items:Set[A]):Histogram[A] = {
    Histogram(items.toSeq)
  }

  def main(argv:Array[String]):Unit = {
    println(Histogram(List("a", "a", "b","b","b")))
    println(Histogram(Array(4.0, 4.0, 3.0, 4.0, 3.0, 2.0, 4.0, 3.0, 4.0, 3.0, 2.0, 1.0, 4.0)))
    println(Histogram(Array(5.0, 3.0, 3.0, 4.0, 4.0, 3.0, 4.0, 1.0, 3.0, 2.0, 4.0, 3.0, 4.0, 3.0, 2.0, 1.0, 4.0)).data)
    println(Histogram(Array(5.0, 3.0, 3.0, 4.0, 4.0, 3.0, 4.0, 1.0, 3.0, 2.0, 4.0, 3.0, 4.0, 3.0, 2.0, 1.0, 4.0)).byFreq)
  }
}
