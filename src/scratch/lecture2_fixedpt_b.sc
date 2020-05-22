def fixedPoint[A](h: A => A, x0: A, goodEnough:(A,A)=>Boolean): A = {
  val v = h(x0)
  if (goodEnough(x0,v))
    x0
  else
    fixedPoint(h, v, goodEnough)
}

def fixedPoint2[A](h: A => A, x0: A): A = {
  fixedPoint(h,x0, (a:A,b:A)=>(a==b))
}

def fixedPointD[A](h: A => A, x0: A)
                  (goodEnough:(A,A)=>Boolean = (a:A,b:A)=>(a==b)): A = {
  val v = h(x0)
  if (goodEnough(x0,v))
    x0
  else
    fixedPoint(h, v, goodEnough)
}

def g3(data: List[Char], open: Char, close: Char): List[Char] = {
  def recur(data: List[Char], acc: List[Char]): List[Char] = {
    data match {
      case op :: cl :: tail =>
        if (op == open && cl == close)
          recur(tail, acc)
        else
          recur(cl :: tail, op :: acc)
      case _ => (data ++ acc).reverse
    }
  }

  recur(data, List())
}

def g(data: List[Char]): List[Char] = {
  g3(data, '(', ')')
}

def f(data: String): String = {
  g(data.toList).mkString
}

f(f(f(")()x((z))((())())y(")))
f("x")

g(")()x((z))((())())y(".toList)

fixedPoint2(f, ")()x((z))((())())y(")
fixedPoint2(g, ")()x((z))((())())y(".toList)
fixedPoint2( g3(_,'(',')'),
            ")()x((z))((())())y(".toList)
fixedPointD( g3(_,'[',']'),
             "][]x[[z]][[[]][]]y[".toList)().mkString
fixedPointD( g3(_,'[',']'),
             "][]x[[z]][[[[][][]]][]]y[".toList)().mkString

