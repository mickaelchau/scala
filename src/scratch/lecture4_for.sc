val as = List(1,2,3,4,5)
val bs = List(10,20,30)
val cs = List(100,200,300,400)


as.flatMap { a =>
  bs.flatMap { b =>
    cs.map { c => a+b+c
    }
  }
}

as.flatMap(a =>
  bs.flatMap(b =>
    cs.map(c => c+b+a)))

as.flatMap(a =>
  bs.flatMap(b =>
    cs.flatMap(c => List(c+b+a))))

for{a <- as
    b <- bs
    c <- cs } yield c+b+a

as.flatMap(a =>
  if(a != 2)
    bs.flatMap(b =>
      cs.map(c => c+b+a))
  else
    List())

for{a <- as
    if a != 2
    aa = a + 1
    b <- bs
    c <- cs } yield c+b+a+aa

for{a <- as
    if a != 2
    aa = a + 1
    b <- bs
    c <- cs } println( c+b+a+aa)

