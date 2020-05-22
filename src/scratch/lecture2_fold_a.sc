import scala.math._
import lecture.MetroData._

//stationPositions
//stationPositions.map(triple => triple._1)
//stationPositions.map(triple => triple._1).distinct
//
//stationPositions.map(triple => triple._1)
//  .distinct
//  .filter(name => ! name.toList.contains(' '))

stationPositions.filter(triple => triple._2 > 300)
  .map(_._1)

stationPositions.toList
  .filter(triple => triple._2 > 300)
  .splitAt(3)






