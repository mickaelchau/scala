import scala.math._

def fixedPoint[A](h:A=>A,x0:A):A = {
  val v=h(x0)
  if (x0 == v)
    x0
  else
    fixedPoint(h,v)
}

def g(data:List[Char]):List[Char] = {
  def recur(data:List[Char],acc:List[Char]):List[Char] = {
    data match {
      case op::cl::tail  =>
        if (op == '(' && cl == ')')
          recur(tail,acc)
        else
          recur(cl::tail,op::acc)
      case _ => (data ++ acc).reverse
    }
  }
  recur(data,List())
}

def f(data:String):String = {
  g(data.toList).mkString
}


f(f(f(")()x((z))((())())y(")))
f("x")

g(")()x((z))((())())y(".toList)

fixedPoint(f,")()x((z))((())())y(")
fixedPoint(g, ")()x((z))((())())y(".toList)








