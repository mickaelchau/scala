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

// In a previous lecture and exercise we implemented the makeAdj_*
//   functions to calculate the so-called adjacency list for a graph.
//   Remember that the adjacency list is not a list, rather it is
//   just called that for historical reasons.
//   In this assignment you will again implement makeAdj but this time
//   using the groupBy function.
//
// You must complete the implementation of several functions.
//    makeAdjDirected
//    reversedEdges
//    makeAdv
//    reachableVertices
//
// In each case you must replace the ??? with the correct code.
//
// makeAdjDirected, to implement this function you need to
//   fill in the body of a call to .groupBy{} and a call to map.{}
//   In each case, one line of code suffices.
//
// reversedEdges, to implement this function, you need to substitute
//   variable names for the ???
//
// makeAdv, to implement this function you must fill in the condition
//   within the if(???) which arbitrates between the two variants
//   of the algorithm.  If in doubt, look at the test cases, and try
//   to run the tests with your proposed implementation.
//
// reachableVertices, you should be able to use
//   the exact implementation you wrote in a previous exercise.
//   Simply copy/paste that solution in place here for the
//   reachableVertices function.   I estimate that you need
//   10 to 15 lines of code including braces { }.

object AdjStdLib {

  // this function computes the Adj list in the form of a Map[V,Set[V]]
  // assuming the given edges are directed.
  def makeAdjDirected[V](edges: Seq[(V,V)]):Map[V,Set[V]] = {
    val f1:((V,V))=>V = ???
    val f2:((V,V))=>V = ???
    edges.groupBy(f1)
      .map{case (src, edges) =>
        val destinations = edges.map(f2)
        src -> destinations.toSet
      }
  }

  // Given a sequence of pairs of the form (src,dst), this
  // function returns a sequence where each pair is reversed
  // to (dst,src).   I.e.,
  // Seq((1,2),(3,4),(5,6)) --> Seq((2,1),(4,3),(6,5))
  def reversedEdges[V](edges: Seq[(V,V)]):Seq[(V,V)] = {
    ??? // edges.map{case (???,???) => (???,???)}
  }

  def makeAdj[V](edges: Seq[(V, V)], directed: Boolean): Map[V, Set[V]] = {
    if (???)
      makeAdjDirected(edges)
    else
      makeAdjDirected(edges ++ reversedEdges(edges))
  }

  def reachableVertices[V](edges: Seq[(V, V)], vStart: V, directed: Boolean): Set[V] = {
    // this should be the body of the reachableVerticies you have already implemented previosly
    ???
  }
}
