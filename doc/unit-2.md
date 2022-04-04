[Back to Unit 1](unit-1.md)  &nbsp;&nbsp;&nbsp;&nbsp; [Forward to Unit 3](unit-3.md)

# Unit 2 -- Recursion

<img src="img/recursive-tree-steps.png" width=700 alt="recursion">

# What is Functional Programming (FP)?

The answer depends on who you ask.  There are several principles:

- Recursion
- Avoiding mutation
- Avoiding side effects
- Pure functions
- Functions as objects
- Higher order functions

There are several current and historical functional programming languages:

- Common Lisp
- Scheme
- ML
- OCaml
- Erlang
- Elixer
- Haskell
- Clojure
- F#
- Scala





# Accessing `Vector` and `List`

- Constructors: `Vector(1,3,4)`, `List(2,3,5,7,11)`
- Accessing `Vector`:   `v(3)`
- Accessing `List`: `v.head`, `v.tail`
- Pattern matching:
```scala
v match {
  case Nil => ...
  case v::Nil => ...
  case v::vs => ...
}
```

## WARNING: `Array` vs `Vector`

We will not using `Array`.  The `Array` type is a type inherited from
Java and is a mutable data structure.  We will avoid mutable data
structure.  There are some exceptions when we cannot avoid `Array`.
- The argument of `main` must by of type `Array[String]`

```scala
def main(argv:Array[String]):Unit = {
  ...
}
```

- The `String` method named `split`, which Scala inherits directly
  from Java returns a `Array[String]`. However, we can call `toList`
  or `toVector` to convert it to a different immutable type.

```scala
  "ab,cd,ef,xyz".split(",")          // returns an `Array[String]`
  "ab,cd,ef,xyz".split(",").toList   // returns an `Array[String]`
  "ab,cd,ef,xyz".split(",").toVector // returns an `Array[String]`
```


# Recursion on `List` and `Vector`


- Introduction to basic concepts in functional programming. 
- A central theme in functional programming is computing results
  without mutating state.
- One common way of achieving this is through a recursive function.

We may implement recursive or iterative function in three principle ways.

- Simple-recursion, 
- Tail-recursion, 
- Reduction using `fold`, `reduce`, and `foldLeft`


# Simple Recursion

A simple recursive function requires stack size proportional to the
length of the `List` or `Array`.  If the data is too large, the stack
will overflow.

Recursion on `Vector` looks very different than recursion on `List`.

```scala
  def sumIntegersBySimpleRecursion(ints: List[Int]): Int = {
    ints match {
      case List() => 0
      // common variable names are also h::t or head::tail  or first::rest
      case i :: is => i + sumIntegersBySimpleRecursion(is)
    }
  }

  def sumIntegersBySimpleRecursion(ints: Vector[Int]): Int = {
    def recur(n:Int): Int = {
      if (n == ints.size)
        0
      else
        ints(n) + recur(n+1)
    }
    recur(0)
  }
```

# Tail Recursion

A tail recursive function requires constant stack size.  The stack
size does not grow with the length of the `List` or `Array`.

```scala
  def sumIntegersByTailRecursion(ints: List[Int]): Int = {
    @scala.annotation.tailrec
    def sumRest(acc: Int, rest: List[Int]): Int = {
      rest match {
        case List() => acc
        case i :: is => sumRest(acc + i, is)
      }
    }

    sumRest(0, ints)
  }

  def sumIntegersByTailRecursion(ints: Vector[Int]): Int = {
    @scala.annotation.tailrec
    def sumRest(acc: Int, n: Int): Int = {
      if (n == ints.size)
        acc
      else
        sumRest(acc + ints(n), n+1)
    }

    sumRest(0, 0)
  }
```

# Using the `fold` function.

The `fold` family of functions hides this syntactical difference,
preserving on the computation but not the recursion pattern itself.

```scala
  def sumIntegersByFold(ints: List[Int]): Int = {
    ints.fold(0)(_ + _)
  }

  def sumIntegersByFold(ints: Vector[Int]): Int = {
    ints.fold(0)(_ + _)
  }
```

# Other `fold` examples

- `fold`
- `foldLeft`
- `reduce`


# `fold` the simple case

How to `fold` with binary function vs unary function accepting a 2-tuple.

Suppose 
- `A` is some type
- `f` is a binary function taking two objects of type `A` 
- `seq` is a _collection_ of whose elements are of type `A`, e.g., `List[A]`, `Vector[A]`
- `z` is an object of type `A`, typically the zero but not necessarily.

Then `fold` returns an element of type `A`, even if `seq` has length 0 or 1.

```scala
  seq.fold(0)(f)
```

Now suppose there is no such _zero_, or suppose we know `seq` is not empty.

```scala
  seq.reduce(0)(f)
```

Now suppose your input and output types are different.  E.g., suppose
you have a sequence, `List[String]`, and you want to sum the string
lengths.  I.e,. the input type is `String` and output type is `Int`.
So not `f` must be a function which takes a `String` and returns an
`Int`; e.g., perhaps it is the function which returns the string
length.  `(str:String)=>str.length`

You cannot use `fold` because the input and output types are different.
You may, however, use `foldLeft`.

```scala
  seq.foldLeft(0)(str=>str.length)
```


Other examples using binary function.


# Support files

## Lecture files
- `src/main/scala/lecture/Recursion.scala`
 
## Homework files
- `src/main/templates/Recursion.scala`
- `src/test/waiting/RecursionTestSuite.scala`
