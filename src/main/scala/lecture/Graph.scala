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

abstract class ProtoGraph[A](val Vertices: Set[A], val Edges: Set[(A, A)]) {
  val edges: Set[Edge[A]] = Edges.map(Edge.pairToEdge)

  val Adj: Map[A, Set[A]] = {
    // vertices which can be reached from v1 with one step
    def successors(v1: A): Set[A] = {
      Vertices.filter { v2: A => edges.contains(Edge(v1, v2)) }
    }

    Vertices.map(v => (v, successors(v))).toMap
  }
}

case class Graph[A](override val Vertices: Set[A], override val Edges: Set[(A, A)])
  extends ProtoGraph[A](Vertices, Edges)
    with BellmanFordFold[A]
    with FloydWarshall[A]{
}

object Graph {
  def verticesToEdges[A](vertices:List[A]):Set[Edge[A]] = {
    val Path(_,edges) = Path.verticesToPath(vertices)
    edges
  }

  def main(args: Array[String]): Unit = {

  }
}