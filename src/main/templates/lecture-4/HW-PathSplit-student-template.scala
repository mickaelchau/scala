// Copyright (c) 2019 EPITA Research and Development Laboratory
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

import lecture.graph._
import lecture.Metro._
import lecture.MetroData._

object PathSplit {
  def splitPathForMetro(routing:Path[Station]):List[Path[Station]] = {
    // The given routing is an instance of Path[A] and represents
    // a shortest path from a source station to a destination station
    // subject to a particular weighting function, which you no longer
    // have access to.
    // The components of the Path, stations and edges, have the following semantics:
    //    stations -- a List[Station] indicating the stations from the source
    //       to the destination.
    //    edges -- a set of Edge[Station] objects.  Each such edge is composed of
    //       to stations, called src and dst, Edge(src,dst), meaning that both
    //       the src and dst Stations are in the stations list.
    // The stations list may contain adjacent duplicate elements, indicating stations
    //    which are transfers from one metro line to another.  For example at
    //    station "Place d'Italie" the passenger may transfer from metro line M-5
    //    to line M-7 (among other possibilities).   The names of the metro lines
    //    are not indicated anywhere in the data structure, and don't actually matter
    //    for this function.
    //    Note however, that the actual elements of the station list are not duplicated,
    //    but the physical station the represent may be duplicated.
    //    For example station 244 represents "Place d'Italie" as does 243.
    //    If the stations list contains 244 followed by 243 this is considered a
    //    duplicate of station "Place d'Italie".
    //    Also note that the first and second station in the list may be duplicates,
    //    and the last and second-to-last may be duplicates.
    // This function, splitPathForMetro, returns a list of objects of type Path[Station],
    //    where no such object contains duplicate stations.
    // For example, if the stations list is List(260, 184, 352, 244, 243, 83, 128),
    //    then the return value of splitPathForMetro will contain one Path containing
    //    (in the same order) List(260, 184, 352, 244) and another Path containing
    //    List( 243, 83, 128).
    // Clues:
    //  1) Given a path you may extract its vertices and edges with pattern matching:
    //       path match { case Path(vertices,edges) => ...}
    //  2) After you have calculated a new list of vertices, in the correct order
    //       you may generate a new path using: verticesToPath(vertices)

    ???
  }

}
