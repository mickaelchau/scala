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

package homework

import scala.annotation.tailrec

object Triptych {
  type Card = Set[String]

  val colors = Set("red", "purple", "green")
  val shapes = Set("oval", "squiggle", "diamond")
  val numbers = Set("one", "two", "three")
  val shadings = Set("solid", "striped", "outlined")

  // The Set of all possible 81 cards.
  val deck:Set[Card] = ???

  // We represent a card as a Set of 4 strings, one string representing color,
  // one string representing shape, one string representing number, and one
  // string representing shading. isTriptych should return true if its
  // argument is a Triptych
  def isTriptych(cards: Set[Card]): Boolean = {
    ???
  }

  // given two cards, c1 and c2, return the uniquely determined 3rd card, c3,
  // for which Set(c1, c2, c3) is a Triptych.
  def completeTriptych(c1: Card, c2: Card): Card = {
    ???
  }

  // Given a set of 0 or more cards, return a Set of all
  // Triptychs which can be formed.  Beware that sometimes the
  // same card can appear in two (or more) different Triptychs.
  // When such is the case, each such Triptych should be included
  // in the return value set.
  def findAllTriptychs(cards: Set[Card]): Set[Set[Card]] = {
    ???
  }

  // Given a set of 0 or more cards, return None if the there are no three
  // cards which form a valid Triptych, otherwise return an
  // Option of that Triptych.  Be aware that more that one Triptych may
  // exist among the given cards.  But an Option of only one should be
  // returned.
  //
  def findTriptych(cards: Set[Card]): Option[Set[Card]] = {
    ???
  }

  // Given a set of cards, and a target size between 3 and 18 inclusive,
  // find a subset of the given size, consisting of cards which contain no
  // Triptych.
  def findCap(cards: Set[Card], targetSize:Int): Option[Set[Card]] = {
    ???
  }
}

object SanityTest {

  import Triptych._

  def main(argv: Array[String]): Unit = {

    assert(81 == deck.size)

    assert(isTriptych(Set(Set("oval", "red", "one", "solid"),
                          Set("oval", "green", "one", "solid"),
                          Set("oval", "purple", "one", "solid"))))

    assert(isTriptych(Set(Set("squiggle", "red", "striped", "three"),
                          Set("oval", "purple", "solid", "two"),
                          Set("diamond", "green", "outlined", "one"))))

    // not three cards
    assert(!isTriptych(Set(Set("oval", "red", "one", "solid"),
                           Set("oval", "purple", "one", "solid"))))
    // not number-compatible
    assert(!isTriptych(Set(Set("squiggle", "red", "striped", "three"),
                           Set("oval", "purple", "solid", "one"),
                           Set("diamond", "green", "outlined", "one"))))
    // invalid feature values
    assert(!isTriptych(Set(Set("w", "x", "y", "z"),
                           Set("a", "b", "c", "d"),
                           Set("1", "2", "3", "4"))))

    // completeTriptych
    assert(Set("red", "squiggle", "solid", "three") ==
             completeTriptych(Set("red", "squiggle", "solid", "two"),
                              Set("red", "squiggle", "solid", "one")))
    assert(Set("oval", "red", "striped", "three") ==
             completeTriptych(Set("oval", "purple", "solid", "one"),
                              Set("oval", "green", "outlined", "two")))
    assert(Set("red", "diamond", "three", "solid") ==
             completeTriptych(Set("red", "oval", "one", "solid"),
                              Set("red", "squiggle", "two", "solid")))

    // findAllTriptychs
    val ensemble1 = Set(Set("red", "squiggle", "solid", "two"),
                        Set("red", "squiggle", "solid", "one"),
                        Set("red", "squiggle", "solid", "three"))
    val ensemble2 = Set(Set("red", "squiggle", "solid", "two"),
                        Set("red", "diamond", "solid", "one"),
                        Set("red", "oval", "solid", "three"))

    assert(findAllTriptychs(ensemble1 union ensemble2).contains(ensemble1))
    assert(findAllTriptychs(ensemble1 union ensemble2).contains(ensemble2))

    // findTriptych
    assert(None == findTriptych(Set()))
    findTriptych(ensemble1 union ensemble2) match {
      case Some(e) => assert(Set(ensemble1, ensemble2).contains(e))
      case None => assert(false, "findTriptych should have found Tryptich in this case")
    }

    // findCap
    findCap(Set(Set("red","squiggle","one","solid"),
                        Set("red","squiggle","two","solid"),
                        Set("red","squiggle","three","solid"),
                        Set("green","oval","one","solid")), 3) match {
      case None => assert(false,"should have found a non-Tryptich")
      case Some(s) => {
        assert(None == findTriptych(s))
      }
    }
  }
}
