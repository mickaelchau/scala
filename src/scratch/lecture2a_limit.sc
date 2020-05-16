import scala.math._

def limit4(f:Double=>Double,dx:Double,dy:Double,a:Double):Double = {
  val f1 = f(a + dx)
  val f2 = f(a + dx / 2)
  if (abs(f1 - f2) < dy)
    f2
  else
    limit4(f, dx / 2, dy, a)
}

limit4(cos,0.1,0.0001,0.0)
limit4(sin,0.1,0.0001,0.0)
limit4(cos,0.1,0.00001,Pi/4)

def limit(dx: Double, dy: Double) = {
  limit4(_,dx,dy,_)
}

val limit2a = limit(0.1, 0.0001)
val limit2b = limit(0.1, 0.000001)
val limit2c = limit(0.1, 0.00000001)

limit2a(sin , Pi)
limit2b(sin , Pi)
limit2c(sin , Pi)

def f(x:Double):Double = {
  (x * x - 1) / (x - 1)
}
f(0)
f(0.999999)
limit2c(f,1.0)