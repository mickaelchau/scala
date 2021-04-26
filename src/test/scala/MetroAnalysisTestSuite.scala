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

import homework.MetroAnalysis._
import lecture.Metro._
import lecture.Path
import org.scalatest.funsuite.AnyFunSuite

class MetroAnalysisTestSuite extends AnyFunSuite {

  val (dst, pred) = metroGraph.floydWarshall(legTimes)
  test("maximizeStationTransfers") {
    import homework.PathSplit.splitPathForMetro

    val (from: String, to: String) = maximizeStationTransfers()
    val (dst, pred) = metroGraph.floydWarshall(legTimes)
    assert(metroGraph.Vertices.exists { ind1 =>
      stationName(ind1) == from &&
        metroGraph.Vertices.exists { ind2 =>
          stationName(ind2) == to &&
            locally {
              val Some(shortest) = metroGraph.FWtracePredecessors(ind1, ind2, pred)
              val path = Path.verticesToPath(shortest)
              splitPathForMetro(path).size == 5 // this is the expected number of colors needed to colorize the longest path
            }
        }
    })
  }
  test("maximizeStationTransitTime"){
    assert( List(("Créteil-Préfecture","Saint-Denis-Université"),
                 ("Saint-Denis-Université","Créteil-Préfecture")).contains(maximizeStationTransitTime()))
  }
  test("maximizeStationTransitLegs"){
    assert( List(("Créteil-Préfecture","Pont de Sèvres"),
                 ("Pont de Sèvres","Créteil-Préfecture")).contains(maximizeStationTransitLegs()))
  }
  test("stationsServedBy") {
    val allStations: Set[String] = (for {
      n <- 1 to 5
      stations = stationsServedBy(n)
      _ = assert(stations.nonEmpty, s"stations served by $n was empty")
      name <- stations
      Some(index) = stationIndex(name)
      _ = assert(metroGraph.Vertices.contains(index), "no such station [$name] $index")
      m = metroGraph.Vertices.filter {
        stationName(_) == name
      }.size
      _ = assert(n == m, s"expecting $m stations, got $n")
    }
      yield name).toSet
    assert(metroGraph.Vertices.map(stationName) == allStations)
  }

  test("uniDirectionPairs") {
    val pairs: Set[(String, String)] = uniDirectionPairs()
    assert(pairs.size == 13)

    for {(from, to) <- pairs}
      assert(metroGraph.Vertices.exists {
        fromIndex =>
          stationName(fromIndex) == from &&
            metroGraph.Vertices.exists {
              toIndex =>
                stationName(toIndex) == to &&
                  metroGraph.Adj(fromIndex).contains(toIndex) &&
                  !metroGraph.Adj(toIndex).contains(fromIndex)
            }
      }, s"$from -> $to is not a valid uni-directional station pair")
  }

  test("findEndStations") {
    // Set(Place Balard, Mairie d'Ivry, Boulogne Pont de Saint-Cloud Rond Point Rhin et Danube, Gabriel Péri Asnières-Gennevilliers, Porte Dauphine, La Courneuve 8 Mai 1945, Mairie de Montreuil, Mairie des Lilas, Porte de la Chapelle, Bibliothèque François Mitterand, Château de Vincennes, Porte de Clignancourt, Pont de Levallois Bécon, Mairie d'Issy, Galliéni, Saint-Denis-Université, Pont de Sèvres, Villejuif Louis Aragon, Châtillon-Montrouge, Porte d'Orléans, Grande Arche de la Défense, Bobigny Pablo Picasso, Créteil-Préfecture)
    val ends: Set[String] = findEndStations()
    assert(ends.size == 23)
    ends.map { end =>
      assert(stationIndex(end).nonEmpty, "no such station [$end]")
      assert(metroGraph.Vertices.exists { index =>
        stationName(index) == end &&
          metroGraph.Adj(index).size == 1 &&
          metroGraph.Adj(index).forall{n=>
            metroGraph.Adj(n).contains(index)
        }
      }, "$end is not an end station")
    }
  }

  test("findTransferStations") {
    val transfers: Set[String] = findTransferStations()
    assert(transfers.size == 58)
    transfers.map { transfer =>
      assert(stationIndex(transfer).nonEmpty, "no such station [$transfer]")
      assert(metroGraph.Vertices.exists { index =>
        stationName(index) == transfer &&
          metroGraph.Adj(index).exists { t2 => stationName(t2) == transfer }
      })
    }
  }

  test("findForks") {
    val forks = findForks()
    assert(forks.size == 2)
    for {station <- forks
         Some(index) = stationIndex(station)
         neighbors = metroGraph.Adj(index).map(stationName) - station
         } {
      assert(metroGraph.Adj(index).size > 2)
      assert(neighbors.size > 2)
    }
  }

  test("stationDistanceTransfers") {
    for {(dist, st1, st2) <- Array((2, "Saint-Marcel", "Péreire"),
                                   (5, "Bercy", "Danube")
                                   )}
      assert(stationDistanceTransfers(st1, st2) == dist)

    val (dst, pred) = metroGraph.floydWarshall(legTimes)
    for {(dist, st1, st2) <- Array((1, "Duroc", "Vaneau"),
                                   (2, "Saint-Marcel", "Péreire"),
                                   (3, "Jasmin", "Rue du Bac"),
                                   (2, "Passy", "Boucicaut"),
                                   (2, "Ranelagh", "Cadet"),
                                   (3, "La Muette", "Place Balard"),
                                   (3, "Bolivar", "Maison Blanche"),
                                   (3, "Rambuteau", "Victor Hugo"),
                                   (4, "Anvers", "Mirabeau"),
                                   (3, "Argentine", "Javel"),
                                   (3, "Voltaire", "Vaugirard"),
                                   (3, "Mairie d'Issy", "Porte de Pantin"),
                                   (3, "Mairie d'Issy", "Porte d'Italie"),
                                   (2, "Europe", "Porte d'Italie"),
                                   (2, "Porte d'Italie", "Porte de Pantin"),
                                   (3, "Saint-Denis-Université", "Créteil-Préfecture"),
                                   (3, "Créteil-Préfecture", "Saint-Denis-Université"),
                                   (3, "Créteil-Préfecture", "Pont de Sèvres"),
                                   (5, "Bercy", "Danube")
                                   )}
      assert(stationDistanceTransfers(pred, st1, st2) == dist)
  }
}
