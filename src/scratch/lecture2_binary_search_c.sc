import scala.math._

// find a root of the given function within the given range
def binSearch(left:Double,right:Double,f:Double=>Double,epsilon:Double,maxDepth:Int):Option[Double] = {

  def recur(left:Double,right:Double,depth:Int):Option[Double] = {
    val m = (left + right) / 2
    val fm = f(m)
    if (depth >= maxDepth)
      None
    else if (abs(fm) < epsilon)
      Some(m)
    else if (fm < 0)
      recur(m, right,depth+1)
    else
      recur(left, m,depth+1)
  }
  if (f(left) <=0 && f(right) >=0)
    recur(left,right,0)
  else if (f(left) >=0 && f(right) <=0)
    recur(right,left,0)
  else
    None
}

binSearch(-Pi/10, Pi/20, sin, 0.0001,32)
binSearch(-Pi/10, Pi/20, cos, 0.0001,32)
binSearch(-10.0,10.0,x=>10-x*x*x,0.0001,32)