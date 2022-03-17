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


// In this exercise, you should take one of the makeAdj_* functions
//   developed in the lecture, and change the name to makeAdj.
//   You must choose one which fulfills the requirements and satisfies
//   the test cases.
//
// ATTENTION: do not use any mutation in this assignment.  You will not
//   receive credit for the assignment if you use mutation.
//
// Your functions will manipulate a graph which may be DIRECTED or
//   UNDIRECTED, and whose vertices may be any type.  But any one graph
//   contains only edges of the same type.  E.g., no graph will have
//   some vertices which are String and others which are Int.
//   The graph will be specified by its edges.  Each edge is a
//   pair of vertices.
//   There are no self loops, I.e., no edge has the same source and destination.
//   And there is at most one edge between any two vertices.
//   The graph has no isolated vertices.
//   The graph is not guaranteed to be connected.
//
// The function makeAdj should take 2 arguments, edges and directed.
//    directed is a Boolean indicating whether the edges should
//    be interpreted as directed or undirected.
//    edges should be a parameter capable of receiving a List of any
//    type or an Vector of any type.  I.e., edges specifies a *collection*
//    of pairs of vertices.  And the vertices may be String, Integer, Double,
//    List, Vector, any type at all, but all vertices are the same type
//    on any one call to makeAdj.
//    In the case that the graph is DIRECTED, there may be vertices
//    which have no successors.  For example there might be an edge from 1 to 2
//    but no edge started at 2.  In this case the Map returned from makeAdj
//    should contain 1 -> Set(... 2 ...), but should not contain 2 -> Set().
//
// Implement the function reachableVertices which takes a parameter representing
//   the graph edges.  The function should return a Set of vertices which
//   can be reached starting from the given starting vertex by tracing edges.
//   Edges are traced in a directed sense if the graph is DIRECTED, and edges
//   may be trace in both directions if the graph is UNDIRECTED.
//   The function should implement a breadth-first search starting at
//   the given vertex, to collect a set of vertices that are reachable from
//   that starting vertex.  E.g., if a graph has only two edges (1,2) and (3,4)
//   then 1 and 2 are reachable starting with 1;  3 and 4 are reachable starting
//   with 3.  If the graph is directed then only 4 is reachable starting with 4,
//   but if the graph is undirected, then both 3 and 4 are reachable starting
//   with 4.
//
// If you attended the THEG graph theory course, then you have already implemented
//   a similar function in Python.  You may have used mutable data structures.
//   You must implement the Scala function using IMMUTABLE data structures.
//   Even if your function passes all the test cases, you will not receive credit
//   for the assignment if your code mutates any of its data structures.
//
// The two functions, makeAdj and reachableVertices, are missing type declarations
//   for their input parameters.  You must provide these.
//
// If you need to, you may implement other function definitions with this Theg object.
//

object Theg {

  def makeAdj[V](edges: Seq[(V, V)], directed: Boolean): Map[V, Set[V]] = {
    ???
  }

  def reachableVertices[V](edges: Seq[(V, V)],
                           vStart: V,
                           directed: Boolean): Set[V] = {
    val adj: Map[V, Set[V]]  = makeAdj(???, ???)
    @tailrec
    def collectVertices(done: Set[V], toDo: Set[V]): Set[V] = {
      if (???)
        done
      else {
        val v1 = toDo.head
        // In this recursive call you must provide the two required arguments,
        //   the next value of done, and the next value of toDo.
        //   The next value of done is the current value with v1 added.
        //   The next value of toDo is the old value, but with v1 removed
        //      and all the neighbors of v1 (which are not in done) added.
        //      You may wish to create several local variables.
        //      You may use + to add an element (nondestructively) to a set,
        //      use - to remove an element (nondestructively) from a set,
        //      use ++ to append two sets (nondestructively), and
        //      use diff for set-difference.
        val neighbors = adj.getOrElse(v1,Set())
        collectVertices(???, ???)
      }
    }

    collectVertices(???, ???)
  }

  // This function computes a Map which assoicates with each integer, the set of
  // vertices which are that distance from the given vertex vStart.
  // Vertices which are not reachable do not appear anywhere in this return value.
  def partitionVeritcesByDistance[V](edges:Seq[(V,V)],
                                     vState:V,
                                     directed:Boolean):Map[Int,Set[V]] = {
    ???
  }
}
