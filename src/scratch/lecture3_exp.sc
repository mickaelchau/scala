import scala.math.pow

def factorial(n: Int): Long = {
  //println(n)
  if (n <= 1)
    1
  else
    n * factorial(n - 1)
}

// The number of terms of each series which are added.
// You may need to increase this number to make the tests pass
// the larger nTerms, the slower the computation, but the more
// accurate the results.
val nTerms = 6

//          x^0   x^1   x^2   x^3
// exp(x) = --- + --- + --- + --- + ...
//           0!    1!    2!    3!
//        = T(0) + T(1) + T(2) + T(3) + ...  s.t. T(n) = T(n-1) * x/n
//
def exp(x: Double): Double = {
  val t0 = 1.0
  val (sum,_) = (1 to nTerms).foldLeft((t0, t0)) {
    case ((acc, prev), n) =>
      val tn = prev * x / n
      (acc + tn, tn)
  }
  sum
}

for {
  x <- List(0.0, 1.0, 1.1, 1.2, 1.5, 2.0, 0.5, 0.5e-4, 0.5e-9)
} println(
  s"x=$x,  exp=${exp(x)},  error=${scala.math.abs(scala.math.exp(x) - exp(x))}"
  )



