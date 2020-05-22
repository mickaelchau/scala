def fixedPoint[A](f:A=>A,data:A,goodEnough:(A,A)=>Boolean):A = {
  val v = f(data)
  if (goodEnough(v,data))
    data
  else
    fixedPoint(f,v,goodEnough)
}

def g1(data:List[Char]):List[Char] = {
  def recur(data:List[Char],acc:List[Char]):List[Char] = {
    data match {
      case c1 :: c2 :: cs =>
        if (c1 == '(' && c2 == ')')
          recur(cs,acc)
        else
          recur(c2::cs,c1::acc)
      case c::cs =>
        recur(cs,c::acc)
      case List() =>
        acc.reverse
    }
  }
  recur(data,List())
}

def g(data:List[Char]):List[Char] = {
  def cmp(a:List[Char],b:List[Char]):Boolean = {
    a == b
  }
  fixedPoint(g1, data, cmp)
}

def f(data:String):String = {
  g(data.toList).mkString
}

f(")()x((z))((())())y(")

