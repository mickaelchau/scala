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

// lecture notes
//   review directed graphs, single source shortest paths
//     edge reduction, and Bellman-Ford algorithm
//   parameterized functions and classes
//   require/ ensuring/ assert
//   mkString
//   operator overloading
//   methods ending with : such as ::
//   class inheritance and traits
//   Map vs map, and '->' notation
//   type alias (reminder)
//   using map to transform data
//   map vs flatMap vs for-comprehension
//   Vector.tabulate(dim)((i: Int) => i*(i+1))

case class Edge[A](src: A, dst: A) {
  def toSet = Set(src, dst)
}

object Edge {
  def pairToEdge[A](pair: (A, A)): Edge[A] = {
    val (src, dst) = pair
    Edge(src, dst)
  }
}
