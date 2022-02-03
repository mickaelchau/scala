// manipulating sequences of numbers

def isEven(i:Int):Boolean = i % 2 == 0
def isOdd(i:Int):Boolean = ! isEven(i)

val r = scala.util.Random
val data = List.fill(32){r.nextInt(50)}

data.map{i => 2*i}
data.map{i => List(i+0.0,i+0.1, i-0.1) }
data.flatMap{i => Vector(i+0.0,i+.1, i-0.1)}
data.flatMap{i => if (isEven(i)) List(i) else List()}

data.toSet.map{i:Int => 2*i}
data.toSet.map{i:Int => Vector(i+0.0,i+.1, i-0.1)}
data.toSet.flatMap{i:Int => Vector(i+0.0,i+.1, i-0.1)}

import scala.math._

data.fold(0){ (acc,i) => acc + i}
data.sum
data.fold(0){ (acc,i) => max(acc, i)}
List(-5,-1,-2,-3,-2,-12).fold(-5){ (acc,i) => max(acc, i)}

data.filter(isOdd).fold(0){ (acc,i) => min(acc, i)}
data.filter(isOdd).reduce{ (acc,i) => min(acc, i)}
data.filter(isEven).reduce{ (acc,i) => max(acc, i)}

data.filter(isOdd).reduce{ (acc,i) => min(acc, i)}
data.filter(_ > 100)
//data.filter(_ > 100).reduce{ (acc,i) => min(acc, i)}
data.filter(isOdd).reduceOption{ (acc,i) => min(acc, i)}
data.filter(_ > 100).reduceOption{ (acc,i) => min(acc, i)}
