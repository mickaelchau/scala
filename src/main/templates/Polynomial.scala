// Copyright (c) 2020,21,22 EPITA Research and Development Laboratory
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

package homework

object Polynomial {
  import scala.math.{pow,sqrt}

  // We represent a polynomial such as 4x^3 - 2x +4
  //    as Map(3->4.0, 1->-1.0, 0->4.0)

  type POLY = Map[Int,Double]

  // given a polynomial and a numerical value, evaluate the polynomial.
  // E.g., evaluate(Map(2->2.2, 0-> -1.1),4.3))
  //       ==> 2.2*pow(4.3,2) - 1.1*pow(4.3,0)
  def evaluate(a:POLY,x:Double):Double = {
    a.foldLeft(0.0){case (acc,(e,c)) => acc + c * pow(x,e)}
  }

  // given two polynomials, add them to form a new polynomial
  // e.g., plus(Map(2->2.2, 0-> -1.1),Map(3->3.3, 0-> -1.1))
  //  ==> Map(3->3.3, 2->2.2, 0-> -2.2)
  def plus(a:POLY,b:POLY):POLY = {
    (a.keys ++ b.keys).map { e =>
      (a.get(e), b.get(e)) match {
        case (Some(c1), Some(c2)) => e -> (c1+c2)
        case (Some(c1), None) => e -> c1
        case (None, Some(c2)) => e -> c2
      }
    }.toMap
  }

  // create a new polynomial by multiplying a given polynomial by a given scalar (Double)
  def scale(s:Double,b:POLY):POLY = {
    ???
  }

  // subtract two polynomials forming a new polynomial
  def subtract(a:POLY,b:POLY):POLY = {
    // this can be done with scale and plus
    ???
  }

  // multiply two polynomials by multiplying two given polynomials.
  // You may find this function challenging to write.  It is not
  // trivial.   Remember that every term in a must be multiplied by every
  // term in b.  You might find it useful to use foldLeft twice,
  // but you may write the code any way you like as long as it
  // obey functional programming principles,  i.e., don't modify
  // variables, and don't use mutable data structures.
  // It may help to use a pencil and paper to multiply polynomials
  // together to notice the pattern.   Good luck!
  def times(a:POLY,b:POLY):POLY = {
    ???
  }

  // this is the polynomial which when multiplied by any polynomial p,
  //   the result is p.  I.e., times(one,p) ==> times(p,one) ==> p
  val one:POLY = ???

  // this is the polynomial which when added to any polynomial p,
  //   the result is p.  I.e., plus(zero,p) ==> plus(p,zero) ==> p
  val zero:POLY = ???

  // raise a polynomial to a positive integer (or 0) power.
  def power(a:POLY,pow:Int):POLY = {
    assert(pow >= 0, s"power is not and should not be implemented for pow < 0: pow=$pow")
    // warning if pow = 100, don't try to do 99 calls to times,
    //   Hint x^(2n) = (x^n)^2, and x^(2n+1) = x * x^(2n), what is x^0 ?, what is x^1 ?
    pow match {
      case 1 => ???
      case 0 => ???
      case n if ??? => // if some function of n
        ???
      case n =>
        ???
    }
  }

  // detect whether two polynomials are close enough to qualify
  // as equal,  i.e., their maximum distance of the cooef of a power is less than epsilon
  def almostEqual(epsilon:Double)(a:POLY,b:POLY):Boolean = {
    val distance = (a.keys ++ b.keys).foldLeft(0.0) { (acc, e) =>
      val diff = a.getOrElse(e, 0.0) - b.getOrElse(e, 0.0)
      acc.max(abs(diff))
    }
    distance < epsilon
  }

  def main(argv:Array[String]):Unit = {
    ()
  }
}
