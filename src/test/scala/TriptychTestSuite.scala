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

import org.scalatest.funsuite.AnyFunSuite

object TestSupport {
  import homework.Triptych.Card
  val shapes = Set("oval", "squiggle", "diamond")
  val numbers = Set("one", "two", "three")
  val shadings = Set("solid", "striped", "outlined")
  val colors = Set("red","purple","green")
  val features = Set(colors, shapes, numbers, shadings)

  def isCard(card: Card): Boolean = {
    (card.size == 4) &&
    features.forall(f => f.exists(x => card.contains(x)))
  }

  def extractFeature(featureValues: Set[String], card: Card): String = {
    (featureValues intersect card).head
  }

  def completeFeatureValue(pair:Set[Card], featureValues:Set[String]):Set[String] = {
    // returns a Set of a single String, the feature value which the
    // pair of cards predicts.
    // E.g., "red","red" ==> "red"
    // E.g., "red","purple" ==> "green"
    val values: Set[String] = pair.map(extractFeature(featureValues, _))
    if (1 == values.size)
      values
    else
      featureValues diff values
  }

  def completeTriptych(c1: Card, c2: Card): Card = {
    // given two cards, predict what the 3rd card must be to form
    // an Triptych.  Such a card always exists and is unique.
    features.flatMap { featureValues => completeFeatureValue(Set(c1, c2), featureValues) }
  }

  def featureCompatible(featureValues: Set[String], cards: Set[Card]): Boolean = {
    Set(1, 3).contains(cards.flatMap(card => featureValues intersect card).size)
  }

  def compatible(cards: Set[Card]): Boolean = {
    features.forall(f => featureCompatible(f, cards))
  }

  def controlFindAllTriptychs(cards: Set[Card]): Set[Set[Card]] = {
    for {
      c1 <- cards
      c2 <- cards
      if c1 != c2
      c3 = completeTriptych(c1, c2)
      if cards.contains(c3)
    } yield Set(c1, c2, c3)
  }

  def shuffle(r: scala.util.Random, deck: List[Card], shuffled: List[Card]): List[Card] = {
    if (Nil == deck)
      shuffled
    else {
      val n = r.nextInt(deck.size)
      val (left, right) = deck.splitAt(n)
      shuffle(r, left ++ right.tail, right.head :: shuffled)
    }
  }

  def extractTriptychs(remainingDeck: List[Card], table: Set[Card], melded: Set[Set[Card]]): (Set[Set[Card]], Set[Card]) = {
    val triptychs = controlFindAllTriptychs(table)
    assert(triptychs == homework.Triptych.findAllTriptychs(table))
    if (Set() == triptychs)
      (melded, table)
    else {
      val triptych = triptychs.head
      val dealSize = math.max(3, remainingDeck.size)

      extractTriptychs(remainingDeck.drop(dealSize),
                       (table diff triptych) ++ remainingDeck.take(dealSize).toSet,
                       melded + triptych)
    }
  }
}

class TriptychTestSuite extends AnyFunSuite {

  import TestSupport.{extractTriptychs, isCard, shuffle}
  import homework.Triptych._

  test("deck") {
    assert(81 == deck.size)
    deck.foreach { card => assert(isCard(card)) }
  }

  test("test isTriptych") {

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
  }

  test("complete triptych") {
    assert(Set("red", "squiggle", "solid", "three") ==
             completeTriptych(Set("red", "squiggle", "solid", "two"),
                              Set("red", "squiggle", "solid", "one")))
    assert(Set("oval", "red", "striped", "three") ==
             completeTriptych(Set("oval", "purple", "solid", "one"),
                              Set("oval", "green", "outlined", "two")))
    assert(Set("red", "diamond", "three", "solid") ==
             completeTriptych(Set("red", "oval", "one", "solid"),
                              Set("red", "squiggle", "two", "solid")))
    for {
      c1 <- deck
      c2 <- deck
      if (c1 != c2)
      c3 = completeTriptych(c1, c2)
      _ = assert(isTriptych(Set(c1, c2, c3)))
      candidate = Set(c1, c2, c3)
    } yield assert(isTriptych(candidate))
  }
  test("test findAllTriptychs") {
    val r = new scala.util.Random(100)
    val shuffled = shuffle(r, deck.toList, Nil)
    val (triptychs, remaining) = extractTriptychs(shuffled.drop(12), shuffled.take(12).toSet, Set())
    triptychs.map { triptych => assert(isTriptych(triptych)) }
    assert(Set() == findAllTriptychs(remaining))
  }

  test("overlapping triptychs") {

    val triptych1 = Set(Set("red", "squiggle", "solid", "two"),
                        Set("red", "squiggle", "solid", "one"),
                        Set("red", "squiggle", "solid", "three"))
    val triptych2 = Set(Set("red", "squiggle", "solid", "two"),
                        Set("red", "diamond", "solid", "one"),
                        Set("red", "oval", "solid", "three"))
    assert(findAllTriptychs(triptych1 union triptych2).contains(triptych1))
    assert(findAllTriptychs(triptych1 union triptych2).contains(triptych2))

    assert(None == findTriptych(Set()))
    findTriptych(triptych1 union triptych2) match {
      case Some(e) => assert(Set(triptych1, triptych2).contains(e))
      case None => assert(false, "findTriptych should have found an triptych in this case")
    }
  }
  test("5-cap a") {
    assert(None == findTriptych(Set(Set("green", "squiggle", "one", "solid"),
                                    Set("green", "oval", "two", "solid"),
                                    Set("green", "diamond", "two", "solid"),
                                    Set("purple", "oval", "one", "solid"),
                                    Set("red", "squiggle", "one", "solid"))))
    assert(None == findTriptych(Set(Set("green","oval","one","solid"),
                                    Set("green","oval","two","solid"),
                                    Set("green","diamond","one","solid"),
                                    Set("purple","oval","one","solid"),
                                    Set("red","squiggle","two","solid"))))
    assert(None != findTriptych(Set(Set("green","oval","one","solid"),
                                    Set("green","oval","two","solid"),
                                    Set("green","diamond","one","solid"),
                                    Set("purple","oval","one","solid"),
                                    Set("red","squiggle","one","solid"))))
  }
  test("5-cap b") {
    assert(None == findTriptych(Set(Set("red", "squiggle", "one", "solid"),
                                    Set("red", "squiggle", "three", "outlined"),
                                    Set("red", "oval", "two", "striped"),
                                    Set("green", "oval", "one", "solid"),
                                    Set("purple", "oval", "two", "striped"))))
  }
  test("5-cap c") {
    println(
     findCap(Set(Set("red", "squiggle", "one", "solid"),
                                    Set("red", "squiggle", "three", "outlined"),
                                    Set("red", "oval", "two", "striped"),
                                    Set("green", "oval", "one", "solid"),
                                    Set("purple", "oval", "two", "striped"),
                                    Set("red", "squiggle", "one", "solid"),
                                    Set("red", "squiggle", "three", "outlined"),
                                    Set("red", "oval", "two", "striped"),
                                    Set("green", "oval", "one", "solid"),
                                    Set("purple", "oval", "two", "striped")),
                           3
                                    ))
  }

  def testCap(n: Int) = {
    val cap: Option[Set[Card]] = findCap(deck, n)
    cap match {
      case None => assert(false, s"did not find a $n-cap from: deck")
      case Some(c) => {
        assert(c.size == n, s"found cap of wrong size, ${c.size}")
        assert(findTriptych(c).isEmpty)
      }
    }
  }
  test("triptych 5") {
    testCap(5)
  }
  test("triptych 15") {
    testCap(15)
  }
  test("triptych 16") {
    testCap(16)
  }
  test("triptych 17") {
    testCap(17)
  }
  test("triptych 18") {
    testCap(18)
  }
  // TODO added tests
  test("triptych 19") {
    testCap(19)
  }
  test("triptych 20") {
    testCap(20)
  }

  test("cap is subset") {
    val smallDeck = Set(Set("oval", "red", "solid", "one"),
                        Set("oval", "red", "solid", "two"),
                        Set("oval", "red", "striped", "one"),
                        Set("oval", "purple", "solid", "one"),
                        Set("squiggle", "red", "solid", "one"))
    val Some(cap1) = findCap(smallDeck, 3)
    assert(cap1.subsetOf(smallDeck),
           s"found $cap1 which is not a subset of $smallDeck"
           )
    for {targetSize <- (3 to 10)
         d <- (targetSize + 1 to deck.size)
         smallDeck = deck.take(d)
         cap <- findCap(smallDeck, targetSize)
         } assert(cap.subsetOf(smallDeck),
                  s"found $cap is not a subset of $smallDeck")
  }

  test("find cap") {
    findCap(Set(Set("red", "squiggle", "one", "solid"),
                Set("red", "squiggle", "two", "solid"),
                Set("red", "squiggle", "three", "solid"),
                Set("green", "oval", "one", "solid")), 3) match {
      case None => assert(false, "should have found a non-triptych")
      case Some(s) => assert(findTriptych(s).isEmpty)
    }

    (3 to 14).foreach(testCap)
  }
}
