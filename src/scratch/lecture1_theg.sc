import lecture.Theg._
import lecture.MetroData

makeAdj_1(3, List((0, 1), (1, 2)))
val adj = makeAdj_1(3, List((0, 1), (0,2), (1, 2)))
adj(0)
adj(2)
makeAdj_1(3, List((0, 1), (0,1), (1, 2)))


makeAdj_4(3, List((0, 1), (0,1), (1, 2)))

(1,2)
(1 -> 2)
1 -> 2

List(1->2, 2->3, 3->4)


val ar = Vector(10,20,10,20,30)
val li = List(20,30,20,30,40,50)
val se = Set(10,20,40, 10,20,30,20,30)
val ma = Map("a" -> 30,
             "b" -> 20)

ma + ("c" -> 40)
ma.contains("c")
ma.get("c")
ma.get("a")
ma("a")

ma.getOrElse("c",42)
ma.getOrElse("a",42)

val ma3 = ma.withDefaultValue(42)
ma3("c")

ma.get("a") match {
  case None => 42
  case Some(v) => v
}

li match {
  case h :: t => h
  case List() => 42
}

val ma2 = Map("a" ->true,
              "b" ->false)
ma2("b")


ar(2)
ar(3)
ar(0)

li(3)
ma("a")
ma("b")

se + 15
se
se ++ Set(15,16,20,17)
se

ar.updated(1,100)
ar

li
100::li
li

li ++ li
ar ++ ar
ar

se - 10
se
se -- Set(10,30)
se.diff(Set(10,30))



//MetroData.stationPositions
//MetroData.stationPositions.toList
//MetroData.stationPositions.toSet
//MetroData.stationPositions.map{
//  case (name,x,y) => name -> (x,y)
//}.toMap
//MetroData.stationPositions.groupBy(_._1)
//MetroData.stationPositions.groupBy(_._1).map{
//  case (name,triple) => (name -> triple.size)
//}