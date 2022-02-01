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

import homework.AdjStdLib._
import org.scalatest.funsuite.AnyFunSuite

class AdjStdLibTestSuite extends AnyFunSuite {

  test("reversed edges") {
    assert(reversedEdges(List[(Int,Int)]()).toList == List())
    assert(reversedEdges(List((1,2))).toList == List((2,1)))
    assert(reversedEdges(List((1,2),(3,4))).toList == List((2,1),(4,3)))
    assert(reversedEdges(Vector[(Int,Int)]()).toList == List())
    assert(reversedEdges(Vector((1,2))).toList == List((2,1)))
    assert(reversedEdges(Vector((1,2),(3,4))).toList == List((2,1),(4,3)))
    assert(reversedEdges(Seq[(Int,Int)]()).toList == List())
    assert(reversedEdges(Seq((1,2))).toList == List((2,1)))
    assert(reversedEdges(Seq((1,2),(3,4))).toList == List((2,1),(4,3)))
  }

  test("building adjacency list") {
    assert(makeAdj(Vector((1, 2)), false).get(2).contains(Set(1)))
    assert(makeAdj(Vector((1, 2)), true).get(2).isEmpty)

    assert(makeAdj(Vector((1,2)),false)
             == Map(1 -> Set(2),
                    2 -> Set(1)))
    assert(makeAdj(List((1,2),(2,3)), false)
             == Map(1 -> Set(2),
                    2 -> Set(1,3),
                    3 -> Set(2)))
    assert(makeAdj(List(("a","b"),("b","c"),("a","c"),("b","d")),true)
             == Map("a" -> Set("b","c"),
                    "b" -> Set("c","d")))
  }

  test("collect connected vertices by Vector") {
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 1, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 2, false) == Set(1, 2, 3, 4), "undirected")
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 3, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 4, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 1, true) == Set(1, 2))
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 2, true) == Set(2), "directed")
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 3, true) == Set(3, 4, 2))
    assert(reachableVertices(Vector((1, 2), (3, 4), (4, 2)), 4, true) == Set(4, 2))
  }
  test("disconnected graph by Vector") {
    // now a disconnected graph

    assert(reachableVertices(Vector((1,2),(1,3),(1,4),(4,1),
                                   (10.1,11),(10.1,12),(11,12)),11,false) == Set(10.1,11,12))
    assert(reachableVertices(Vector((1,2),(1,3),(1,4),(4,1),
                                   (10.1,11),(10.1,12),(11,12)),11,true) == Set(11,12))
    assert(reachableVertices(Vector((1,2),(1,3),(1,4),(4,1),
                                   (10.1,11),(10.1,12),(11,12)),1,false) == Set(1,2,3,4))
    assert(reachableVertices(Vector((1,2),(1,3),(1,4),(4,1),
                                   (10.1,11),(10.1,12),(11,12)),1,true) == Set(1,2,3,4))
  }

  test("collect connected vertices by List") {
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 1, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 2, false) == Set(1, 2, 3, 4), "undirected")
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 3, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 4, false) == Set(1, 2, 3, 4))
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 1, true) == Set(1, 2))
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 2, true) == Set(2), "directed")
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 3, true) == Set(3, 4, 2))
    assert(reachableVertices(List((1, 2), (3, 4), (4, 2)), 4, true) == Set(4, 2))
  }
  test("disconnected graph by List") {
    // now a disconnected graph

    assert(reachableVertices(List((1,2),(1,3),(1,4),(4,1),
                                  (10,11),(10,12),(11,12)),11,false) == Set(10,11,12))
    assert(reachableVertices(List((1,2),(1,3),(1,4),(4,1),
                                  (10,11),(10,12),(11,12)),11,true) == Set(11,12))
    assert(reachableVertices(List((1,2),(1,3),(1,4),(4,1),
                                  (10,11),(10,12),(11,12)),1,false) == Set(1,2,3,4))
    assert(reachableVertices(List((1,2),(1,3),(1,4),(4,1),
                                  (10,11),(10,12),(11,12)),1,true) == Set(1,2,3,4))
  }

  test("disconnected graph with doubles") {
    // now a disconnected graph

    assert(reachableVertices(List((1.1,2.2),(1.1,3.3),(1.1,4.4),(4.4,1.1),
                                  (10.1,11.1),(10.1,12.2),(11.1,12.2)),11.1,false) == Set(10.1,11.1,12.2))
    assert(reachableVertices(List((1.1,2.2),(1.1,3.3),(1.1,4.4),(4.4,1.1),
                                  (10.1,11.1),(10.1,12.2),(11.1,12.2)),11.1,true) == Set(11.1,12.2))
    assert(reachableVertices(List((1.1,2.2),(1.1,3.3),(1.1,4.4),(4.4,1.1),
                                  (10.1,11.1),(10.1,12.2),(11.1,12.2)),1.1,false) == Set(1.1,2.2,3.3,4.4))
    assert(reachableVertices(List((1.1,2.2),(1.1,3.3),(1.1,4.4),(4.4,1.1),
                                  (10.1,11.1),(10.1,12.2),(11.1,12.2)),1.1,true) == Set(1.1,2.2,3.3,4.4))
  }
  test("disconnected graph with List[String]") {
    // now a disconnected graph

    assert(reachableVertices(List(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"john",false) == Set("germaine","john","emilienne"))
    assert(reachableVertices(List(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"john",true) == Set("john","emilienne"))
    assert(reachableVertices(List(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"rosalie",false) == Set("rosalie","gilbert","theophile","fred"))
    assert(reachableVertices(List(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"rosalie",true) == Set("rosalie","gilbert","theophile","fred"))
  }
  test("disconnected graph with Vector[String]") {
    // now a disconnected graph

    assert(reachableVertices(Vector(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"john",false) == Set("germaine","john","emilienne"))
    assert(reachableVertices(Vector(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"john",true) == Set("john","emilienne"))
    assert(reachableVertices(Vector(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"rosalie",false) == Set("rosalie","gilbert","theophile","fred"))
    assert(reachableVertices(Vector(("rosalie","gilbert"),("rosalie","theophile"),("rosalie","fred"),("fred","rosalie"),
                                  ("germaine","john"),("germaine","emilienne"),("john","emilienne")),"rosalie",true) == Set("rosalie","gilbert","theophile","fred"))
  }
}
