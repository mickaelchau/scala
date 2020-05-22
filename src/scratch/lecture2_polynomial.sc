import lecture.Polynomial._
val p1 = Map(1->11.3, 2->10.3, 3-> -3.1)
val p2 = Map(0->2.1, 2->10.3, 1-> -3.0)
val  exponents = p1.keys ++ p2.keys

p1.get(2)
p1.get(0)

type POLY = Map[Int,Double]

def plus(p1:POLY, p2:POLY):POLY = {
  val exponents = p1.keys ++ p2.keys
  exponents.map{
    e => (p1.get(e),p2.get(e)) match {
      case (Some(c1),Some(c2)) => e -> (c1+c2)
      case (None,Some(c2)) => e -> c2
      case (Some(c1),None) => e -> c1
    }
  }.toMap
}

plus(p1,p2)
import scala.math.pow

def evaluate(p:POLY, x:Double):Double = {
  p.foldLeft(0.0){
    case (acc,(e,c)) => acc + pow(x,e) * c
  }
}

evaluate(p1,0.0)
evaluate(p2,0.0)
