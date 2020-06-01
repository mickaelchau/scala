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

package homework

import lecture.Path
import lecture.Metro._
import lecture.MetroData._
import lecture.Edge

object MetroAnalysis {
  // Return a Set of Strings containing the names of all the
  // stations served by exactly the given number of metro stations.
  def stationsServedBy(numMetros:Int):Set[String] = {
    // to count how many metros serve a named station, count how many
    //   times it appears in the stationPositions array.
    stationPositions.groupBy(???).toList.filter {
      case (name: String, v: Array[(String, Int, Int)]) => ???
    }.map(???).toSet
  }

  // Most stations in the paris metro system have a metro running in
  // both directions.  However, there is a small number of stations
  // which are only served in one direction,
  //     E.g., Mirabeau, and Port d'Auteuil
  // The following function finds a set of pairs (src,dst) for which
  // there is a one step connection from src to dst BUT NOT from
  // dst to src.
  def uniDirectionPairs():Set[(String,String)] = {
    for{ from <- metroGraph.Vertices
         to <- metroGraph.Adj(from)
         if (???)
         } yield (stationName(from),stationName(to))
  }

  // most stations connect to two stations.  However,
  // on some metro lines a station serves
  // more than two stations.  Example, the station "Maison Blanche".
  // This function finds all stations which serve more than two
  // stations on the same metro line.
  def findForks():Set[String] = {
    for {from <- metroGraph.Vertices
         toStationsSameLine = ???
         if (??? > 2)
         } yield stationName(from)
  }

  // an end station is a station which only connects to 1 other station.
  // compute a set of end stations (as strings)
  def findEndStations():Set[String] = {
    for {from <- metroGraph.Vertices
         if (???)
         } yield stationName(from)
  }

  // a transfer station is where two different metro lines meet.
  // such a station has a name string which corresponds to two
  // different indices (integers)
  // compute the set of all such station names (strings)
  def findTransferStations():Set[String] = {
    for {from <- metroGraph.Vertices
         ??? // you may need multiple lines here
         } yield stationName(from)
  }

  // Given two station names, compute the distance (in seconds)
  //  from st1 to st2.
  def stationDistanceTime(st1:String, st2:String):Int = {
    val (dst,pred) = metroGraph.floydWarshall(legTimes)
    val Some(ind1) = stationIndex(st1)
    val Some(ind2) = stationIndex(st2)
    dst((ind1,ind2))
  }

  // Given two station names, compute the distance (in number of legs)
  //  from st1 to st2.
  def stationDistanceLegs(st1:String,st2:String):Int = {
    val (dst,pred) = metroGraph.floydWarshall(Function.const(1))
    val Some(ind1) = stationIndex(st1)
    val Some(ind2) = stationIndex(st2)
    dst((ind1,ind2))
  }

  // the *distance* between two stations may be measured in three
  //  different ways,
  //    1) transit time between the stations
  //    2) number of legs in the shortest path between the stations
  //    3) number of metro lines needed to connect the two stations
  // Distances 1 and 2 can be achieved by examining the 1st value
  //   returned by metroGraph.floydWarshall as shown in functions
  //   stationDistanceTime and stationDistanceLegs shown above.
  // Your task here is to implement stationDistanceTransfers
  //   which calculates distance #3.
  //
  // Given two station names, compute the distance (in number of metro
  // lines s needed) from st1 to st2. If one metro transfer is needed,
  // that's two metro lines.
  // This function takes, pred, as first argument.  pred is the
  // 2nd value returned from metroGraph.floydWarshall()
  def stationDistanceTransfers(pred:Map[(Int,Int),Int],st1:String,st2:String):Int = {
    import homework.PathSplit.splitPathForMetro
    val Some(ind1) = stationIndex(st1)
    val Some(ind2) = stationIndex(st2)

    val Some(shortest) =  metroGraph.FWtracePredecessors(ind1, ind2, pred)
    val path = Path.verticesToPath(shortest)

    // you have written a function to split such a path into
    //  a sequence of paths, use that function to count the
    //  number of such paths.
    ???
  }

  // The following function is useful for testing, but is slow
  //   if called multiple times, because it calls floydWarshall
  //   every time it is called.
  def stationDistanceTransfers(st1:String,st2:String):Int = {
    val (dst, pred) = metroGraph.floydWarshall(legTimes)
    stationDistanceTransfers(???,???,???)
  }

  // given a function, f, which can be passed to metroGraph.floydWarshall(...)
  //   find two station (name strings) which are a maximum distance appart.
  def maximizeStationBy(f:Edge[Station]=>Int):(String,String) = {
    val (dst,pred) = metroGraph.floydWarshall(f)

    ???
  }

  def maximizeStationTransitTime():(String,String) = {
    maximizeStationBy(legTimes)
  }

  def maximizeStationTransitLegs():(String,String) = {
    maximizeStationBy(Function.const(1))
  }

  // find any two stations (strings) for which the
  //   number of metro lines needed to connect them is maximum.
  def maximizeStationTransfers():(String,String) = {
    import homework.PathSplit.splitPathForMetro
    val (dst, pred) = metroGraph.floydWarshall(legTimes)
    val allDist = for {fromIndex <- 0 until stationPositions.size
                       toIndex <- 0 until stationPositions.size
                       shortest <- metroGraph.FWtracePredecessors(fromIndex, toIndex, pred)
                       path = Path.verticesToPath(shortest)
                       s = splitPathForMetro(path)
                       } yield (s.size, fromIndex, toIndex)
    val (maxTransfers, seq) = allDist.groupBy(_._1).toList.sortBy(_._1).reverse.head
    val (st1, st2) = (seq(0)._2, seq(0)._3)

    (stationName(st1), stationName(st2))
  }

  def main(argv:Array[String]):Unit = {
    println("uni direction " + uniDirectionPairs())
    println("forks " + findForks())
    println("transfers " + findTransferStations())
    println("end stations " + findEndStations())
    println(maximizeStationTransitLegs())
    println(maximizeStationTransitTime())
    println(maximizeStationTransfers())
  }
}
