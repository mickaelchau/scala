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

trait BellmanFordFold[A] extends ProtoGraph[A] {
  type DP = (Map[A, Int], Map[A, A])

  ///////////////////
  // Bellman-Ford single source shortest path
  ///////////////////

  def bellmanFord(source: A, weight: Edge[A] => Int): DP = {
    // DP is a pair of two Maps.
    //   1) the total weight calculated so far from source to a given vertex.
    //   2) the predecessors (u -> v) where the shortest path yet calculated
    //        from source to v, has u as the penultimate vertex.
    //type DP = (Map[A, Int], Map[A, A])

    def relax(edge: Edge[A], dp: DP): DP = {
      val (distance, predecessor) = dp
      val Edge(u, v) = edge
      lazy val w = weight(edge)

      // distance.get(x) returns either None meaning the
      //   best distance yet found between source and x is infinite.
      //   otherwise distance.get(x), returns Some(dx)
      //   meaning the best distance calculated so far between
      //   source and x is dx.
      //   If distance.get(u) is None, then we don't have any update
      //   for dp on this iteration.
      //   Else if distance.get(v) is None, then we
      //   add ( v -> (du + w)) to the distance map,
      //   and (v -> u) to the predecessor map.
      //   Else in the case that distance.get(u) and also distance.get(v)
      //   both are Some(...), then we only update the two maps
      //   if the new distance found (du + w) is less than the
      //   previous best distance dv.
      (distance.get(u), distance.get(v)) match {
        case (None, _) => dp // no distance to u yet calculated
        case (Some(du), None) => // known distance to u, but not to v,
          //                     // so we can calculate dist to v by du+w
          (distance + (v -> (du + w)), predecessor + (v -> u))
        case (Some(du), Some(dv)) if du + w < dv => // found better route to v, via u
          (distance + (v -> (du + w)), predecessor + (v -> u))
        case _ => dp
      }
    }

    // The outer foldLeft, effectively loops |Vertices|-1 times,
    //   as required by the Bellman-Ford algorithm.
    //   The inner foldLeft relaxes each of the edges.
    (1 until Vertices.size).foldLeft((Map(source -> 0), Map()): DP) {
      (dp: DP, _: Int) => {
        edges.foldLeft(dp) {
          (dp: DP, edge: Edge[A]) => relax(edge, dp)
        }
      }
    }
  }

  def BFshortestPath(src: A, dst: A)( weight: Edge[A] => Int): Option[List[A]] = {
    val (_, predecessor) = bellmanFord(src, weight)

    // If there is a path from src to dst, then its trace is now in the
    // predecessor mapping.  We find it one vertex at at time, by starting
    // at dst, and work our way back.  If there is no such path, we return
    // None, otherwise we return Some(path)
    @scala.annotation.tailrec
    def tracePredecessors(path: List[A]): Option[List[A]] = {
      path match {
        case v1 :: _ if src == v1 => Some(path)
        case v1 :: _ =>
          predecessor.get(v1) match { // is there a predecessor of v1?
            case None => None
            case Some(v2) => tracePredecessors(v2::path)
          }
        case _ => None // won't happen because tracePredecessors is called with Path(List(dst)...)
      }
    }
    tracePredecessors(List(dst))
  }
}
