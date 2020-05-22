import scala.math._

def almostEqual(epsilon:Double)(a:Double,b:Double):Boolean = {
  abs(a-b) < epsilon
}

def limit(f:Double=>Double,dx:Double,test:(Double,Double)=>Boolean)(a:Double):Double = {
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

def derivative(f:Double=>Double,dx:Double,test:(Double,Double)=>Boolean)(x:Double):Double = {
  def estimate(h:Double):Double = {
    (f(x+h) - f(x) ) / h
  }
  limit(estimate,dx:Double,test)(0)
}

val cos_est:Double=>Double = derivative(sin,0.01,almostEqual(0.000001)) // cos
val poly_est:Double=>Double = derivative(x => x*x + 3*x + 1,0.01,almostEqual(0.00001)) // 2*x + 3

for {n <- 0 to 20
     x = -Pi + n * 2 * Pi / 20
     c1 = cos(x)
     c2 = cos_est(x)
     delta = c1 - c2
     } println(s"$x  delta= $delta")

//derivative(sin)(Pi/2)
//derivative(x => x*x + 3*x + 1)(1)