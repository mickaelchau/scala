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

case class Path[A](vertices: List[A], edges: Set[Edge[A]]) {
  require(vertices.size > 0, "Path with no vertices is not supported")
  require(edges.forall { edge =>
    // for each given edge, verify that src,dst are adjacent
    // in the vertices list.
    //  TODO need to also verify that for each pair of consecutive vertices
    //    that an edge exists.
    (vertices.head :: vertices).tails.exists {
      case a :: b :: _ => {
        a == edge.src && b == edge.dst
      }
      case _ => false
    }
  }, s"an edge is out of sync with vertices: edges=$edges vertices=$vertices")

  val numVertices: Int = vertices.size
  val vertexSet: Set[A] = vertices.toSet
  val head: A = vertices.head

  override def toString: String = {
    vertices.mkString("[", "->", "]")
  }

  // create a new Path by consing a new vertex to the beginning of the path, and updating
  //  the edge list with the newly formed edge.
  //  Note that operators ENDING with : are read from right to left, other operators
  //  are read from left to right.
  def ::(vertex: A): Path[A] =
    Path(vertex :: vertices, edges + Edge(vertex, vertices.head)) // we know vertices is not empty
}

object Path {
  // Create a path from a ordered list of vertices.
  // This deduces the set of edges correctly according to the given
  //   order of vertices.
  def verticesToPath[A](vertices: List[A]): Path[A] = {
    @tailrec
    def deduceEdges(acc: Set[Edge[A]], vertices: List[A]): Set[Edge[A]] = {
      vertices match {
        case _ :: Nil => acc
        case Nil => acc
        case v1 :: v2 :: vs => deduceEdges(acc + Edge(v1, v2), v2 :: vs)
      }
    }

    Path(vertices, deduceEdges(Set(), vertices))
  }
}
