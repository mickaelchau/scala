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

object Theg {

  def checkEdge(n: Int, src: Int, dst: Int): Unit = {
    assert(src >= 0, s"edge src=$src must be >=0")
    assert(dst >= 0, s"edge dst=$dst must be >=0")
    assert(src < n, s"edge src=$src must be < n=$n")
    assert(dst < n, s"edge dst=$dst must be < n=$n")
  }

  // we begin assuming the edges are DIRECTED. This makes the code
  //   simpler.   Later we will parameterize the function to allow
  //   directed or undirected graphs.

  // tail recursive function
  //   iterates on List input
  //   produces Vector output
  //   uses .isEmpty, .head, and .tail to examine List and terminate recursion
  //   uses .updated to extend Vector
  //   uses + to extend Set
  def makeAdj_1(n: Int, edges: List[(Int, Int)]): Vector[Set[Int]] = {
    val adj = Vector.fill(n)(Set[Int]())

    @scala.annotation.tailrec
    def recur(edges: List[(Int, Int)], adj: Vector[Set[Int]]): Vector[Set[Int]] = {
      if (edges.isEmpty) // if (edges == List())
        adj
      else {
        val (src, dst) = edges.head
        checkEdge(n, src, dst)
        recur(edges.tail,
             adj.updated(src, adj(src) + dst)) // immutable, nondestructive operation
      }
    }

    recur(edges, adj)
  }

  // tail recursive function
  //   iterates on List input
  //   produces Vector output
  //   * uses pattern matching to examine list and terminate recursion
  //   uses .updated to extend Vector
  //   uses + to extend Set
  def makeAdj_2(n: Int, edges: List[(Int, Int)]): Vector[Set[Int]] = {
    val adj = Vector.fill(n)(Set[Int]())

    @scala.annotation.tailrec
    def recur(edges: List[(Int, Int)], adj: Vector[Set[Int]]): Vector[Set[Int]] = {
      edges match {
        case (src, dst) :: tail =>
          checkEdge(n, src, dst)
          recur(tail,
               adj.updated(src, adj(src) + dst))
        case List() =>
          adj
      }
    }

    recur(edges, adj)
  }

  // tail recursive function
  //   * iterates on Vector input
  //   produces Vector output
  //   * uses .size and Vector access by index to examine input Vector
  //   uses .updated to extend Vector
  //   uses + to extend Set
  def makeAdj_3(n: Int, edges: Vector[(Int, Int)]): Vector[Set[Int]] = {
    val adj = Vector.fill(n)(Set[Int]())

    @scala.annotation.tailrec
    def recur(i: Int, adj: Vector[Set[Int]]): Vector[Set[Int]] = {
      if (i == edges.size)
        adj
      else {
        val (src, dst) = edges(i)
        checkEdge(n, src, dst)
        recur(i + 1,
             adj.updated(src, adj(src) + dst))
      }
    }

    recur(0, adj)
  }

  // tail recursive function
  //   * iterates on List input
  //   * produces Map output
  //   * uses pattern matching on List
  //   * uses + to extend Map
  //   uses + to extend Set
  def makeAdj_4(n: Int, edges: List[(Int, Int)]): Map[Int, Set[Int]] = {
    val adj = (0 to n - 1).map { i => i -> Set[Int]() }.toMap

    @scala.annotation.tailrec
    def loop(adj: Map[Int, Set[Int]], edges: List[(Int, Int)]): Map[Int, Set[Int]] = {
      edges match {
        case (src, dst) :: es =>
          loop(adj + (src -> (adj(src) + dst)), es)
        case _ => adj
      }
    }

    loop(adj, edges)
  }

  // * uses tabulate to iterate
  //   * iterates on Vector input
  //   * produces Vector output
  //   * computes Vector once, not iteratively, not recursively
  //   * uses .filter .toSet to compute set of destination vertices
  def makeAdj_5(n: Int, edges: Vector[(Int, Int)]): Vector[Set[Int]] = {
    // compute the set of neighbors of vertex i
    def connectionsTo(i: Int): Set[Int] = {
      (0 until n).filter {
        j => edges.exists { case (src, dst) => src == i && dst == j }
      }.toSet
    }

    Vector.tabulate(n)(connectionsTo)
  }

  // uses tabulate to iterate
  //   iterates on Vector input
  //   produces Vector output
  //   computes Vector once, not iteratively, not recursively
  //   * uses .contains .toSet to compute set of destination vertices
  def makeAdj_6(n: Int, edges: Vector[(Int, Int)]): Vector[Set[Int]] = {
    // compute the set of neighbors of vertex i
    def connectionsTo(i: Int): Set[Int] = {
      (0 until n).filter {
        j => edges.contains(i -> j)
      }.toSet
    }

    Vector.tabulate(n)(connectionsTo)
  }

  // tail recursive function
  //   iterates on List input
  //   produces Map output
  //   uses pattern matching on List
  //   uses + to extend Map
  //   uses + to extend Set
  //   * omits initialization of Map, just uses Map() and .getOrElse
  def makeAdj_7(edges: List[(Int, Int)]): Map[Int, Set[Int]] = {

    @scala.annotation.tailrec
    def loop(adj: Map[Int, Set[Int]], edges: List[(Int, Int)]): Map[Int, Set[Int]] = {
      edges match {
        case (src, dst) :: es =>
          loop(adj + (src -> (adj.getOrElse(src, Set()) + dst)),
               es)
        case _ => adj
      }
    }

    loop(Map(), edges)
  }

  // * uses .foldLeft
  //   iterates on List input
  //   produces Map output
  //   uses + to extend Map
  //   uses + to extend Set
  //   omits initialization of Map, just uses Map()
  //   * uses .withDefaultValue rather than  .getOrElse
  def makeAdj_8(edges: List[(Int, Int)]): Map[Int, Set[Int]] = {
    val initial = Map[Int, Set[Int]]().withDefaultValue(Set())
    
    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        adj + (src -> (adj(src) + dst))
    }
  }

  // uses .foldLeft
  //   * iterates on Vector input
  //   * code copied exactly from makeAdj_8
  //   produces Map output
  //   uses + to extend Map
  //   uses + to extend Set
  //   omits initialization of Map, just uses Map()
  //   uses .withDefaultValue rather than  .getOrElse
  def makeAdj_9(edges: Vector[(Int, Int)]): Map[Int, Set[Int]] = {
    val initial = Map[Int, Set[Int]]().withDefaultValue(Set())

    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        adj + (src -> (adj(src) + dst))
    }
  }

  // uses .foldLeft
  //   * iterates on Seq input or List input
  //   * code copied exactly from makeAdj_7/makeAdj_8
  //   produces Map output
  //   uses + to extend Map
  //   uses + to extend Set
  //   omits initialization of Map, just uses Map()
  //   uses .withDefaultValue rather than  .getOrElse
  def makeAdj_10(edges: Seq[(Int, Int)]): Map[Int, Set[Int]] = {
    val initial = Map[Int, Set[Int]]().withDefaultValue(Set())

    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        adj + (src -> (adj(src) + dst))
    }
  }

  // uses .foldLeft
  //   * parameterizes the vertex type, no longer Int, now is V
  //   * iterates on Seq[A] input or List[A] input
  //   produces Map output
  //   uses + to extend Map
  //   uses + to extend Set
  //   omits initialization of Map, just uses Map()
  //   uses .withDefaultValue rather than  .getOrElse
  def makeAdj_11[V](edges: Seq[(V, V)]): Map[V, Set[V]] = {
    val initial = Map[V, Set[V]]().withDefaultValue(Set())
    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        adj + (src -> (adj(src) + dst))
    }
  }

  // same as makeAdj_11, but assumes UNDIRECTED edges
  def makeAdj_12[V](edges: Seq[(V, V)]): Map[V, Set[V]] = {
    val initial = Map[V, Set[V]]().withDefaultValue(Set())
    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        adj +
          (src -> (adj(src) + dst)) +
          (dst -> (adj(dst) + src))
    }
  }

  // combine makeAdj_11 and makeAdj_12 with a Boolean indicating
  // whether edges are interpreted as DIRECTED or UNDIRECTED
  def makeAdj_13[V](edges: Seq[(V, V)], directed: Boolean): Map[V, Set[V]] = {
    val initial = Map[V, Set[V]]().withDefaultValue(Set())
    edges.foldLeft(initial) {
      case (adj, (src, dst)) =>
        val m1 = adj + (src -> (adj(src) + dst))
        if (directed)
          m1
        else
          m1 + (dst -> (adj(dst) + src))
    }
  }

  def main(args: Array[String]): Unit = {
    println("1: " + makeAdj_1(3, List((0, 1), (1, 2))))
    println("1: " + makeAdj_1(3, List((0, 1), (1, 2))).mkString("Vector[",",","]"))
    println("2: " + makeAdj_2(3, List((0, 1), (1, 2))).mkString(","))
    println("3: " + makeAdj_3(3, Vector((0, 1), (1, 2))).mkString(","))
    println("4: " + makeAdj_4(3, List((0, 1), (1, 6), (0, 2))))
    println("5: " + makeAdj_5(3, Vector((0, 1), (1, 2), (0, 2))).mkString(","))
    println("6: " + makeAdj_6(3, Vector((0, 1), (1, 2), (0, 2))).mkString(","))
    println("7: " + makeAdj_7(List((0, 1), (1, 6), (0, 2))))
    println("8: " + makeAdj_8(List((0, 1), (1, 6), (0, 2))))
    println("9: " + makeAdj_9(Vector((0, 1), (1, 6), (0, 2))))
    println("10: " + makeAdj_10(List((0, 1), (1, 6), (0, 2))))
    println("10: " + makeAdj_10(Vector((0, 1), (1, 6), (0, 2))))
    println("11: " + makeAdj_11(Vector((0, 1), (1, 6), (0, 2))))
    println("11: " + makeAdj_11(Vector(("fred", "jane"), ("sally", "rita"), ("rita", "fred"))))
    println("11: " + makeAdj_11(List(("fred", "jane"), ("sally", "rita"), ("rita", "fred"))))
    println("12: " + makeAdj_12(List(("fred", "jane"), ("sally", "rita"), ("rita", "fred"))))
    println("13a: " + makeAdj_13(List(("fred", "jane"), ("sally", "rita"), ("rita", "fred")), true))
    println("13b: " + makeAdj_13(List(("fred", "jane"), ("sally", "rita"), ("rita", "fred")), false))
  }
}