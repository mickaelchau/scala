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

def integral(f:Double=>Double,left:Double,right:Double,test:(Double,Double)=>Boolean):Double = {
  def sumRectangles(partitionWidth: Double): Double = {
    val numPartitions = ((right - left) / partitionWidth).floor.toInt
    (1 to numPartitions).foldLeft(0.0)( (acc, i) => acc + f(left + i * partitionWidth) * partitionWidth )
  }
  //limit(sumRectangles,(right - left)/2,test)(0)

  if (right == left)
    0.0
  else if (right < left)
    - integral(f, right, left, test)
  else
    limit(sumRectangles,(right - left)/2,test)(0)

}

for {n <- 0 to 20
     x = -Pi + n * 2 * Pi / 20
     s1 = sin(x)
     s2 = integral(cos,0,x,almostEqual(0.00001))
     delta = s1 - s2
     } println(s"$x  delta= $delta")

def doubleRange(lower:Double, upper:Double, steps:Int) = {
  val step = (upper - lower)/steps
    for { i <- (0 to steps).view
          x = lower+ i*step
          } yield x.min(upper)
}
(for {x <- doubleRange(-Pi, Pi, 21)
     c = cos(x)} println(s"x=$x cos(x)=$c"))

