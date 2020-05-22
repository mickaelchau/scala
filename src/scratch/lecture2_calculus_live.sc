import scala.math._

def limit(f:Double=>Double,x0:Double,h:Double,test:(Double,Double)=>Boolean):Double = {
  def recur(h:Double):Double = {
    val f1 = f(x0 + h)
    val f2 = f(x0 + h / 2)
    if (test(f1, f2))
      f2
    else
      recur( h / 2)
  }
  recur(h)
}

def almostEqual(epsilon:Double)
               (a:Double,b:Double):Boolean = {
  abs(a - b) < epsilon
}

def derivative(f:Double=>Double,dx:Double,test:(Double,Double)=>Boolean)
              (x:Double):Double = {
  def estimate(h:Double):Double = {
    (f(x+h) - f(x)) / h
  }
  limit(estimate,0,dx,test)
}

val cos_est = derivative(sin,0.1,almostEqual(0.000001))(_)

for {n <- 0 to 20
     x = -Pi + n*2 * Pi / 20
     c1 = cos(x)
     c2 = cos_est(x)
     delta = c1 - c2
     } println(s"delta = $delta")

type BooleanBinary = (Double,Double)=>Boolean

def integral(f:Double=>Double, left:Double, right:Double, test:BooleanBinary):Double = {
  def sumRectangles(partitionWidth:Double):Double = {
    val numPartitions = ((right-left)/partitionWidth).floor.toInt
    (1 to numPartitions).foldLeft(0.0){
      (acc,i) => acc + partitionWidth * f(left + i*partitionWidth)
    }
  }
  limit(sumRectangles,0.0,(right-left)/2,test)
}

val sin_est = (x => integral(cos,0.0,x,almostEqual(0.0001)))

for {n <- 0 to 20
     x = 0 + n*2 * Pi / 20
     c1 = sin(x)
     c2 = sin_est(x)
     delta = c1 - c2
     } println(s"delta = $delta")



