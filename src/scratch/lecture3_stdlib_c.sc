def isEven(i:Int):Boolean = i % 2 == 0
def isOdd(i:Int):Boolean = ! isEven(i)

val r = scala.util.Random
val items = Array("a","b","c","d","x","y")
val data = List.fill(32){(r.nextInt(50),items(r.nextInt(items.size)))}

import scala.math._
data.foldLeft((data.head._1,data.head._1)){
  case ((low,high),(i,_c)) =>
    (min(low,i),max(high,i))
}

data.map(_._1).foldLeft((data.head._1,data.head._1)){
  case ((low,high),i) =>
    (min(low,i),max(high,i))
}
