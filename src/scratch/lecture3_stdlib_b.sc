def isEven(i:Int):Boolean = i % 2 == 0
def isOdd(i:Int):Boolean = ! isEven(i)

val r = scala.util.Random
val data = List.fill(32){r.nextInt(50)}

data.map{i => 2*i}
data.map{i => Array(i+0.0,i+.1, i-0.1)}
data.flatMap{i => Array(i+0.0,i+.1, i-0.1)}

data.toSet.map{i:Int => 2*i}
data.toSet.map{i:Int => Array(i+0.0,i+.1, i-0.1)}

import scala.math._

data.fold(0){ (acc,i) => acc + i}
data.fold(0){ (acc,i) => max(acc, i)}
data.filter(isOdd).fold(0){ (acc,i) => min(acc, i)}
data.filter(isOdd).reduce{ (acc,i) => min(acc, i)}

data.filter(isOdd).reduce{ (acc,i) => min(acc, i)}
data.filter(_ > 100)
//data.filter(_ > 100).reduce{ (acc,i) => min(acc, i)}
data.filter(isOdd).reduceOption{ (acc,i) => min(acc, i)}
data.filter(_ > 100).reduceOption{ (acc,i) => min(acc, i)}
