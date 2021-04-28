// Copyright (c) 2020,21 EPITA Research and Development Laboratory
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

package lecture.tree

sealed abstract class Tree[A] {
  def leafData():List[A]

  def apply(a:Int):Int = {
    a
  }
}

case class TreeNode[A](branches:List[Tree[A]]) extends Tree[A] {
  override def toString:String = {
    branches.map(_.toString).mkString("[", ",", "]")
  }
  def leafData():List[A] = {
    branches.flatMap{b => b.leafData()}
  }
}

case class TreeLeaf[A](data:A) extends Tree[A] {
  override def toString:String = {
    data.toString
  }
  def leafData():List[A] = {
    List(data)
  }
}

object Tree {

  def apply[A](x:Int):TreeLeaf[A] = {
    ???
  }
  def contains[A](t:Tree[A],target:A):Boolean = {
    t match {
      case TreeLeaf(data) => target == data
      case TreeNode(branches) => branches.exists{ b=> contains(b,target)}
    }
  }

  def main(argv:Array[String]):Unit = {
    val t3 = TreeLeaf(3.0)
    val t4 = TreeLeaf(4.0)
    val t5 = TreeLeaf(5.0)
    val t6 = TreeLeaf(6.0)

    val t3456 = TreeNode(List(
      TreeNode(List(t3,t4)),
      TreeNode(List(t5,t6))))

    val t = TreeNode(List(t3456,t3456,t6,t3))

    println(contains(t,6.0))
    println(contains(t,7.0))
    println(t.leafData())

    t(1)

  }
}

