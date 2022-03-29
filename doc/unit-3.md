[Back to Unit 2](unit-2.md)  &nbsp;&nbsp;&nbsp;&nbsp; [Forward to Unit 4](unit-4.md)

# Unit 3 -- Graph Theory applications

In this lecture we will review recursive functions and the `fold`
operation by looking some familiar graph algorithms which you already
so in THEG, and which you already implemented in Python.  We'll use
this topic to talk about dealing with types, in particular `Vector`,
`List`, and `Set`.

We will develop functions to compute the so-called *adjacency-list*.
Recall that the adjacency-list, is not necessarily represented by a
`List` type in the programming language, but represents the list of
connections per vertex in a graph.


# Pattern matching

Various ways to extract components from `Tuple` and `List`.

```scala
val (a,b,c) = (1,2,3)

val t = (1,2,3)
val m = List(1,2,3)

t match {
  case (_,b,c) => println(s"b=$b  c=$c")
}

val b = t._2
val c = t._3

m match {
  case Nil => ... // or case List() =>
  case a::Nil => ...
  case a::as => ...
}

```

# Overivew


The lecture will cover the following topics:

- Graph theory review: simple graph (simple, undirected, non-weighted, no self-loops)
- Implement a function to compute adjacency-list, given a collection of edges
- Write a function which accepts `List` or `Vector`
- BFS (breadth-first search)
- Type-parameters for functions
- We will also look at functional graph traversal.
- Pattern matching
- Immutable data structures
- Tuples: notation `_._1`, `_._2` etc
- `Map[]` type
- `List[]` type
- `Set[]` type
- `filter`, `exists`, `contains`, `getOrElse`, `withDefaultValue`
- Parameterized types

# Support files

## Lecture files
- `src/main/scala/lecture/Theg.scala`
 
## Homework files
- `src/main/templates/Theg.scala`
- `src/main/templates/AdjStdLib.scala`

## Test files
- `test/scala/ThegTestSuite.scala`
- `test/scala/AdjStdLibTestSuite.scala`

