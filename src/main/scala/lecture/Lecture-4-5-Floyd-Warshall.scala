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

trait FloydWarshall[A] extends ProtoGraph[A] {

  ///////////////////
  // Floyd-Warshall multiple source shortest path
  ///////////////////

  // A is the type of a vertex, it may be Int, or Double, or anything
  // else compatible with the given weight function.
  //type ADJ[A] = Map[A,Set[A]]

  // Returns a tuple of two objects: a distance Map and a predecessor Map
  def floydWarshall(weight: Edge[A] => Int): (Map[(A,A),Int],Map[(A,A),A]) = {

    val d: Map[(A, A), Int] = for {(src, dsts) <- Adj
                                   dst <- dsts + src
                                   w = if (src==dst) 0 else weight(Edge(src, dst))
                                   } yield (src -> dst) -> w
    val pred: Map[(A, A), A] = for {(src, dsts) <- Adj
                                    dst <- dsts
                                    } yield (src, dst) -> src

    Vertices.foldLeft((d, pred)) { case ((d, pred), k) =>
      Vertices.foldLeft((d, pred)) { case ((d, pred), row) =>
        Vertices.foldLeft((d, pred)) { case ((d, pred), col) =>
          val rc = (row, col)
          (d.get(rc), d.get((row, k)), d.get((k, col))) match {
            case (Some(rowcol), Some(rowk), Some(kcol)) if rowk + kcol < rowcol =>
              // we had an old distance, but we have a better distance via vertex k
              (d + (rc -> (rowk + kcol)), pred + (rc -> pred(k -> col)))

            case (None, Some(rowk), Some(kcol)) =>
              // old distance was INFINITY, but we have a new distance via vertex k
              (d + (rc -> (rowk + kcol)), pred + (rc -> pred((k, col))))

            case (_, _, _) => (d, pred)
          }
        }
      }
    }
  }

  def FWtracePredecessors(src:A, dst:A,predecessor:Map[(A,A),A]):Option[List[A]] = {
    // If there is a path from src to dst, then its trace is now in the
    // predecessor mapping.  We find it one vertex at at time, by starting
    // at dst, and work our way back.  If there is no such path, we return
    // None, otherwise we return Some(path)
    @scala.annotation.tailrec
    def tracePredecessors(path: List[A]): Option[List[A]] = {
      path match {
        case v1 :: _ if src == v1 => Some(path)
        case v1 :: _ =>
          predecessor.get(src,v1) match { // is there a predecessor of v1?
            case None => None
            case Some(v2) => tracePredecessors(v2 :: path)
          }
        case _ => None // won't happen because tracePredecessors is called with Path(List(dst)...)
      }
    }
    tracePredecessors(List(dst))
  }

  def FWshortestPath(src: A, dst: A)(weight: Edge[A] => Int): Option[List[A]] = {
    val (_,predecessor) = floydWarshall(weight)
    FWtracePredecessors(src,dst,predecessor)
  }
}

