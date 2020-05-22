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

package homework
import lecture.Calculus.derivative

// Assignment name: Implement the gradient of a binary funciton
//
// This homework assignment accompanies section "Convergence".
// The test cases can be found in the file GradientTestSuite.scala
// You should complete the function, replacing ??? with correct Scala
// code so that the tests pass.

object Gradient {
  def gradient(f:(Double,Double)=>Double, delta:Double, test:(Double,Double)=>Boolean,x:Double,  y:Double): (Double,Double) = {
    val fx = f(x,_) // a function of y
    val fy = f(???,???) // a function of x
    val partialWRTx = derivative(???,???,???) _ // function of x
    val partialWRTy = derivative(???,???,???) _ // function of y

    // now the return value as a tuple
    (???,???)
  }
}
