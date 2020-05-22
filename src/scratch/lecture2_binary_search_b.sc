import scala.math._

// find a root of the given function within the given range
def binSearch(left:Double,right:Double,f:Double=>Double,epsilon:Double):Option[Double] = {
  // assume f(left) <=0 and f(right) >=0

  def recur(left:Double,right:Double):Double = {
    val m = (left + right) / 2
    val fm = f(m)
    if (abs(fm) < epsilon)
      m
    else if (fm < 0)
      recur(m, right)
    else
      recur(left, m)
  }

  if (f(left) <=0 && f(right) >=0) // increasing
    Some(recur(left,right))
  else if (f(left) >=0 && f(right) <=0) // decreasing
    Some(recur(right,left))
  else
    None
}

binSearch(-Pi/10, Pi/20, sin, 0.0001)
binSearch(-Pi/10, Pi/20, cos, 0.0001)
binSearch(-10.0,10.0,x=>10-x*x*x,0.0001)

