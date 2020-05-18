import scala.math._

// find a root of the given function within the given range
def binSearch(left:Double,right:Double,f:Double=>Double,epsilon:Double):Double = {
  // assume f(left) <=0 and f(right) >=0

  val m = (left + right) / 2
  val fm = f(m)
  if (abs(fm) < epsilon)
    m
  else if (fm < 0)
    binSearch(m,right,f,epsilon)
  else
    binSearch(left,m,f,epsilon)
}

binSearch(-Pi/10, Pi/20, sin, .0001)

