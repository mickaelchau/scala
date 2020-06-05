import lecture.MetroData._

stationPositions.take(5)
legData.take(5)

stationPositions.zipWithIndex.take(5)

// substitute specific station info into leg data
// res3
legData.map{
  case (src,dst,seconds) =>
    (stationPositions(src)
    ,stationPositions(dst)
    ,seconds)
}
// now substitute just the station names
// res4
legData.map{
  case (src,dst,seconds) =>
    (stationPositions(src)._1
    ,stationPositions(dst)._1
    ,seconds)
}

// now just the ones from a station to itself
// res5
legData.map{
  case (src,dst,seconds) =>
    (stationPositions(src)._1
    ,stationPositions(dst)._1
    ,seconds)
}.filter{
  case (src,dst,seconds) => src == dst
}

// does every transfer take 120 seconds?
// res6
legData.map{
  case (src,dst,seconds) =>
    (stationPositions(src)._1
    ,stationPositions(dst)._1
    ,seconds)
}.filter{
  case (src,dst,seconds) => src == dst && seconds != 120
}

// find the end stations

legData.groupBy(_._1).take(3)
legData.groupBy(_._1).filter{
  case (src,legs) => legs.size == 1
}

// res 8 -- print names of end stations
legData.groupBy(_._1).filter{
  case (src,legs) => legs.size == 1
}.map{
  case (src,legs) => stationPositions(src)._1
}.foreach(println)

// res10
legData.groupBy(_._1).map{
  case (src,legs) if legs.size == 1 => stationPositions(src)._1
  case _ => ""
}

// res11
legData.groupBy(_._1).map{
  case (src,legs) if legs.size == 1 => stationPositions(src)._1
  case _ => ""
}.filter(_ != "")

// res12
legData.groupBy(_._1).flatMap{
  case (src,legs) if legs.size == 1 => Some(stationPositions(src)._1)
  case _ => None
}

// res13 --- rewrite res11 as for comprehension
for { (src,legs) <- legData.groupBy(_._1)
      if legs.size == 1
      } yield stationPositions(src)._1
