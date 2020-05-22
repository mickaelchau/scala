import scala.math._

// find a root of the given function within the given range
def binSearch(left:Double,right:Double,f:Double=>Double,epsilon:Double):Option[Double] = {
  def recur(left: Double, right: Double): Option[Double] = {
    val m = (right + left) / 2.0
    val fm = f(m)

    if (abs(fm) < epsilon)
      Some(m)
    else if (fm < 0)
      recur(m, right)
    else
      recur(left, m)
  }
  if ( f(left) <= 0 && f(right) >=0 )
    recur(left,right)
  else if (f(left) >=0 && f(right) <=0 )
    recur(right,left)
  else
    None
}


binSearch(-Pi/10, Pi/20, sin, .0001)
binSearch(-Pi/10, Pi/20, x => 10 - x*x, .0001)