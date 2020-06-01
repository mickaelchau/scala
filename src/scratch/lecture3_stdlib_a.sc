// manipulating lists of numbers
//
def isEven(i:Int):Boolean = i % 2 == 0
def isOdd(i:Int):Boolean = ! isEven(i)

val r = scala.util.Random
val data = List.fill(32){r.nextInt(50)}

data.count(i => i%2 == 0)
data.count(_%2 == 0)
data.count(isEven)
data.count(i => ! isEven(i))
data.count(isOdd)


val a = data.take(5)
val b = data.drop(5)
val (d,e) = data.splitAt(5)

data.takeWhile(i => i < 20)
data.dropWhile(_ < 20)
data.span(_ < 20)

data.groupBy(_ % 5)
data.groupBy(isEven)
data.groupBy(i => i)
data.groupBy(identity)

data.groupBy(identity).map{
  case (k,v) => k -> v.size
}

data.groupBy(_ % 5).map{
  case (k,v) => k -> v.size
}

data.distinct
data.distinct.sorted
data.distinct.sortWith{_ > _}
data.distinct.sortBy{_ % 10}
data.distinct.sortBy{_ % 10}.reverse
