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

object Metro {

  import lecture.MetroData._

  val stationName: Array[String] = stationPositions.map(_._1)

  def stationIndex(name: String): Option[Int] = {
    stationName.indexOf(name) match {
      case -1 => None
      case i => Some(i)
    }
  }

  type Station = Int
  type Time = Int
  type Leg = (Station, Station, Time)

  def getSrc(leg:Leg):Station = leg match {
    case (src:Station,_,_) => src
  }

  def getDst(leg:Leg):Station = leg match {
    case (_,dst,_) => dst
  }

  def getLeg(leg:Leg):(Station,Station) = leg match {
    case (src,dst,_) => (src,dst)
  }

  val sources: Set[Station] = (legData map getSrc).toSet
  val destinations: Set[Station] = (legData map getDst).toSet
  val stations: Set[Station] = sources.union(destinations) // the vertices of the metro graph
  val legs: Set[(Station, Station)] = (legData map getLeg).toSet // the edges of the metro graph

  val stationToXY: Array[(String, (Station, Station))] = stationPositions map {
    case (name, x, y) => name -> (x, y)
  }

  val legTimes: Map[Edge[Station], Time] = (legData map { leg: Leg => {
    val (src, dst, time) = leg
    Edge(src, dst) -> time
  }
  }).toMap
  val metroGraph: Graph[Station] = Graph(stations, legs)

  def main(args: Array[String]): Unit = {
    val Some(station1) = stationIndex("Mairie d'Issy")
    val Some(station2) = stationIndex("Porte d'Italie")
    val Some(ls:List[Station]) = metroGraph.BFshortestPath(station1, station2)( legTimes)
    val Path(indices, edges) = Path.verticesToPath(ls)
    println(s"ls=    $ls")
    println(s"indices= $indices")
    println(s"edges=   $edges")
    println(s"${edges.size}:\n  " + indices.map(stationName).mkString("[", "->", "]"))
  }
}
