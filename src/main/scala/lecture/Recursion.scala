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

object Recursion {
  def sumIntegersBySimpleRecursion(ints: List[Int]): Int = {
    ints match {
      case List() => 0
      // common variable names are also h::t or head::tail  or first::rest
      case i :: is => i + sumIntegersBySimpleRecursion(is)
    }
  }

  def sumIntegersByTailRecursion(ints: List[Int]): Int = {
    @scala.annotation.tailrec
    def sumRest(acc: Int, rest: List[Int]): Int = {
      rest match {
        case List() => acc
        case i :: is => sumRest(acc + i, is)
      }
    }

    sumRest(0, ints)
  }

  def sumIntegersByFold(ints: List[Int]): Int = {
    ints.fold(0)(_ + _)
  }

  def genLongList(len: Int, value: Double): List[Double] = {
    List.tabulate(len)(_ => value)
  }

  def main(argv: Array[String]): Unit = {
    val longList = List.tabulate(12)(n => 0) // List.fill(...)(...)
    println(s"sum = ${sumIntegersBySimpleRecursion(longList)}")
  }
}
