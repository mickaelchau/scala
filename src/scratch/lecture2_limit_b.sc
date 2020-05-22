import scala.math._

def almostEqual(epsilon:Double)(a:Double,b:Double):Boolean = {
  abs(a-b) < epsilon
}

def limit(f:Double=>Double,dx:Double,test:(Double,Double)=>Boolean)
         (a:Double):Double = {
  def recur(dx:Double):Double = {
    val f1 = f(a + dx)
    val f2 = f(a + dx / 2)
    if (test(f1,f2))
      f2
    else
      recur(dx / 2)
  }
  recur(dx)
}

limit(cos,0.1, almostEqual(0.0001))( 0.0)
limit(sin,0.1, almostEqual(0.0001))( 0.0)
limit(cos,0.1, almostEqual(0.00001))( Pi/4)

//val limit2a =
//  limit(_,0.1, almostEqual(0.0001))(_)
val limit2b:(Double=>Double,Double)=>Double =
  limit(_,0.1, almostEqual(0.0001))(_)
val limit2c:(Double=>Double,Double)=>Double =
  limit(_,0.1, almostEqual(0.000001))(_)
val limit2d:(Double=>Double,Double)=>Double =
  limit(_,0.1, almostEqual(0.00000001))(_)

//limit2a(sin , Pi)
limit2b(sin , Pi)
limit2c(sin , Pi)
limit2d(sin , Pi)

def f(x:Double):Double = {
  (x * x - 1) / (x - 1)
}
f(0)
f(0.999999)
limit2c(f,1.0)

