// Copyright (c) 2020 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

import org.scalatest.FunSuite
import lecture.Polynomial._
import scala.math._

class PolynomialTestSuite extends FunSuite {

  import lecture.Polynomial._

  val polynomials: Array[POLY] = Array(one,
                                       zero,
                                       Map(0 -> 3, 1 -> 0.1, 2 -> -0.5),
                                       Map(1 -> 1.0, 2 -> 2.0),
                                       Map(2 -> -2.0, 3 -> 0.125, 1 -> 1.0),
                                       Map(2 -> -2.1, 3 -> 0.125, 1 -> 1.0, 0 -> -0.5),
                                       Map(2 -> -2.2, 3 -> 0.125, 1 -> 1.0),
                                       Map(2 -> -2.3, 3 -> 0.125, 1 -> 1.0, 0 -> -0.25),
                                       Map(2 -> -2.4, 3 -> 0.125, 1 -> 1.0),
                                       Map(2 -> -2.3, 3 -> 0.125, 1 -> -0.5, 0 -> -0.25))
  val xs: Array[Double] = Array(1.0, 0.5, 0.25, -0.8, 0.75)

  test("evaluate") {
    for {p <- polynomials
         x <- xs}
      evaluate(p, x)

    for {x <- xs}
      assert(abs(evaluate(Map(2 -> 1), x) - x * x) < 0.001, "x=$x n=$n")
    for {n <- 0 to 10
         x <- xs}
      assert(abs(evaluate(Map(n -> 1), x) - pow(x, n)) < 0.001, "x=$x n=$n")
    for {x <- xs
         c1 <- List(0.5, 0.25)
         c2 <- List(0.5, 0.25)
         y = c1 * pow(x, 3) + c2 * pow(x, 2) - 1.0
         p = Map(3 -> c1, 2 -> c2, 0 -> -1.0)}
      assert(abs(y - evaluate(p, x)) < 0.0001)

    for {x <- xs} assert(1.0 == evaluate(one, x))
    for {x <- xs} assert(0.0 == evaluate(zero, x))
  }

  test("plus") {
    for {p1 <- polynomials
         p2 <- polynomials
         } plus(p1, p2)
    // check commutativity
    for {p1 <- polynomials
         p2 <- polynomials
         } assert(almostEqual(0.001)(plus(p1, p2), plus(p2, p1)))
    // check associativity
    for {p1 <- polynomials
         p2 <- polynomials
         p3 <- polynomials
         } assert(almostEqual(0.001)(plus(plus(p1, p2),p3), plus(p1,plus(p2, p3))))

    for {x <- xs
         p1 <- polynomials
         p2 <- polynomials
         p12 = plus(p1, p2)
         p1x = evaluate(p1, x)
         p2x = evaluate(p2, x)
         p12x = evaluate(p12, x)
         } assert(abs(p1x + p2x - p12x) < 0.0001, s"p1=$p1 p2=$p2 p12=$p12 x=$x p1x=$p1x p2x=$p2x  p12x=$p12x")
  }

  test("scale") {
    for {p <- polynomials
         s <- Array(0.0, 1.0, -1.0, 0.5, 0.25, 0.125)
         } scale(s, p)

    for {p <- polynomials
         s <- Array(0.0, 1.0, -1.0, 0.5, 0.25, 0.125)
         x <- xs
         spx = evaluate(scale(s,p),x)
         px = evaluate(p,x)
         } assert(abs(spx - s*px) < 0.0001)
  }

  test("subtract") {
    for {p1 <- polynomials
         p2 <- polynomials
         } subtract(p1, p2)
    for {x <- xs
         p1 <- polynomials
         p2 <- polynomials
         p12 = subtract(p1, p2)
         p1x = evaluate(p1, x)
         p2x = evaluate(p2, x)
         p12x = evaluate(p12, x)
         } assert(abs(p1x - p2x - p12x) < 0.0001, s"p1=$p1 p2=$p2 p12=$p12 x=$x p1x=$p1x p2x=$p2x  p12x=$p12x")
    for {p1 <- polynomials
         p2 <- polynomials
         p12a = subtract(p1, p2)
         p12b = plus(p1,scale(-1.0,p2))
         }  assert(almostEqual(0.0001)(p12a,p12b))
  }

  test("times") {
    for {p1 <- polynomials
         p2 <- polynomials
         } times(p1, p2)
    // check commutativity
    for {p1 <- polynomials
         p2 <- polynomials
         } assert(almostEqual(0.001)(times(p1, p2), times(p2, p1)))
    // check associativity
    for {p1 <- polynomials
         p2 <- polynomials
         p3 <- polynomials
         } assert(almostEqual(0.001)(times(times(p1, p2),p3), times(p1,times(p2, p3))))

    for {x <- xs
         p1 <- polynomials
         p2 <- polynomials
         p12 = times(p1, p2)
         p1x = evaluate(p1, x)
         p2x = evaluate(p2, x)
         p12x = evaluate(p12, x)
         } assert(abs(p1x * p2x - p12x) < 0.0001, s"p1=$p1 p2=$p2 p12=$p12 x=$x p1x=$p1x p2x=$p2x  p12x=$p12x")

  }

  test("one") {
    for {p <- polynomials
         } assert(almostEqual(0.0001)(p, times(one, p)))
    for {p <- polynomials
         } assert(almostEqual(0.0001)(p, times(p,one)))
  }

  test("zero") {
    for {p <- polynomials
         } assert(almostEqual(0.0001)(p, plus(zero, p)))
    for {p <- polynomials
         } assert(almostEqual(0.0001)(p, plus(p,zero)))
    for {p <- polynomials
         } assert(almostEqual(0.0001)(zero, times(zero, p)))
    for {p <- polynomials
         } assert(almostEqual(0.0001)(zero, times(p,zero)))
  }

  test("power") {
    for {n <- 1 to 20}
      assert(almostEqual(0.0001)(zero, power(zero, n)), s"zero to any positive power ($n) should be zero")
    for {n <- 1 to 20}
      assert(almostEqual(0.0001)(one, power(one, n)), "one to any positive power should be 1")
    for {p <- polynomials}
      assert(almostEqual(0.0001)(one, power(p, 0)), "one raised to zero should be one")
    for {p <- polynomials}
      assert(almostEqual(0.0001)(p, power(p, 1)), "p raised to one should be p")

    for {x <- xs
         poly <- polynomials
         n <- 0 to 4
         p1 = power(poly, n)
         polyx = evaluate(poly, x) // Double
         p1x = evaluate(p1, x) // Double
         polyxn = pow(polyx, n) // Double
         } assert(abs(p1x - polyxn) < 0.0001, s"poly=$poly n=$n x=$x")
  }
}
