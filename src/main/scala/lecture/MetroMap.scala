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

import scalafx.scene.paint.Color
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Line

import lecture.Metro._
import lecture.MetroData._

trait MetroMap extends JFXApp {

  val (maxX: Int, maxY: Int) = stationPositions.foldLeft((0, 0)) {
    case ((maxX, maxY), (_, x, y)) => (math.max(x, maxX), math.max(y, maxY))
  }
  val (minX: Int, minY: Int) = stationPositions.foldLeft((maxX, maxY)) {
    case ((minX, minY), (_, x, y)) => (math.min(x, minX), math.min(y, minY))
  }
  val drawableLegs: Set[Edge[Station]] = legs.foldLeft(Set[Edge[Station]]()) {
    // uniquify the legs in the sense that either src -> dst is there
    // or dst -> src, but not both.  This is to avoid a leg getting
    // drawn twice in the image, or worst drawn twice with two different
    // colors.
    case (seen: Set[Edge[Station]], (src: Station, dst: Station)) => {
      if (seen.contains(Edge(dst, src)))
        seen
      else
        seen + Edge(src, dst)
    }
  }

  def edgesToLines(legs: Set[Edge[Station]], color: Color, width: Int): Set[Line] = {
    // convert a Set of edges to a Set of Line objects.  A Line object
    // is something which can be given to JFXApp for plotting in a canvas.
    for {
      Edge(src, dst) <- legs
      (_, srcX, srcY) = stationPositions(src)
      (_, dstX, dstY) = stationPositions(dst)
      line = new Line { // offset the line by shifting by minX,minY
        startX = srcX - minX
        startY = maxY - srcY
        endX = dstX - minX
        endY = maxY - dstY
      }
      _ = {
        line.setFill(color)
        line.setStroke(color)
        line.setStrokeWidth(width)
      }
    } yield line
  }

  def drawMetroMapShortestPath(fromStation: Station, toStation: Station): Unit

  def drawMetroMapShortestPath(src: String, dst: String): Unit = {
    val fromIndex = stationIndex(src)
    val toIndex = stationIndex(dst)
    (fromIndex, toIndex) match {
      case (Some(fromStation), Some(toStation)) => drawMetroMapShortestPath(fromStation, toStation)
      case _ => ()
    }
  }

  val metroMapLines: Set[Line] = edgesToLines(drawableLegs, Green, 1)

  def displayMetroMap(routes: Set[Line], fromStation: Station, toStation: Station): Unit = {
    stage = new JFXApp.PrimaryStage {
      title.value = s"Paris Metro: from ${stationName(fromStation)} to ${stationName(toStation)}"
      width = maxX - minX // limit width and height to be only the x,y pairs used in the metro map
      height = maxY - minY
      scene = new Scene {
        fill = White
        content = metroMapLines ++ routes
      }
    }
  }
}

object singleColorShortestPathMetroMap extends MetroMap {

  def drawMetroMapShortestPath(fromStation: Station, toStation: Station): Unit = {
    // draw a metro map with the shortest path routing between fromStation and toStation
    // highlighted in a single color.
    val Some(shortestPath:List[Station]) = metroGraph.BFshortestPath(toStation, fromStation)( legTimes)
    val Path(_, edges:Set[Edge[Station]]) = Path.verticesToPath(shortestPath)
    val routes = edgesToLines(edges, Red, 4)

    displayMetroMap(routes, fromStation, toStation)
  }

  //drawMetroMapShortestPath("Mairie d'Issy", "Porte d'Italie")
  //drawMetroMapShortestPath("Mairie d'Issy", "Porte de Pantin")
  drawMetroMapShortestPath(  "Saint-Denis-Université","Créteil-Préfecture")
}

object multiColorShortestPathMetroMap extends MetroMap {

  /// import homework.PathSplit.splitPathForMetro

  def drawMetroMapShortestPath(fromStation: Station, toStation: Station): Unit = {
    // draw a metro map with the shortest path routing between fromStation and toStation
    // highlighted in a different color.
    val Some(shortestPath: List[Station]) = metroGraph.BFshortestPath(toStation, fromStation)(legTimes)
    val p: Path[Station] = Path.verticesToPath(shortestPath)

    val paths: Seq[Path[Station]] = ??? // splitPathForMetro(p)
    val colors = Vector(DarkOrange, Blue, Red, Gold, Orange, Violet)
    val multiColorRoutes = paths.foldLeft((0, Set[Line]())) {
      case ((colorIndex, lines), Path(_, edges)) =>
        ((colorIndex + 1) % colors.length, lines ++ edgesToLines(edges, colors(colorIndex), 4))
    }._2

    displayMetroMap(multiColorRoutes, fromStation, toStation)
  }

  //drawMetroMapShortestPath("Mairie d'Issy", "Porte de Pantin")
  //drawMetroMapShortestPath("Mairie d'Issy", "Porte d'Italie")
  //drawMetroMapShortestPath("Europe", "Porte d'Italie")
  //drawMetroMapShortestPath("Porte d'Italie", "Porte de Pantin")
  drawMetroMapShortestPath(  "Saint-Denis-Université","Créteil-Préfecture")

  // Créteil-Préfecture,Saint-Denis-Université
  // (Créteil-Préfecture,Pont de Sèvres)
  //drawMetroMapShortestPath("Bercy","Danube")
}
