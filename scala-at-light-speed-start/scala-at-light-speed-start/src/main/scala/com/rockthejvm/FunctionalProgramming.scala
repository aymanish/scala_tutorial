package com.rockthejvm

object FunctionalProgramming extends App {

  // Scala is OOP language
  // RECAP:
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years.")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // both are the same above

  // Scala runs on the JVM
  // JVM knows what an object is not what a function is as a first class citizen
  // Functional programming: use them as first class components
  // - compose functions
  // - pass funcs as args
  // - return functions as results
  // JVM cant do this so scala invented Function X
  // we can instantiate traits as functions

  // Function1 is a trait that represents a function with input A: Int and output B: Int
  // val simpleIncrementer = new Function1[Int, Int]
  val simpleIncrementer = new ((Int) => Int) {
    override def apply(x: Int): Int = x + 1
  }
  // val increment = (x: Int) => x + 1 | Simpler version of above

  simpleIncrementer.apply(23)
  simpleIncrementer(23)
  // we've basically defined a function above
  //  1) Pass functions as arguments:
  //     You can pass simpleIncrementer as an argument to another function.
  //  2) Return functions as results:
  //     A function can return simpleIncrementer or another function, thanks to FunctionX traits.
  //  3) Compose functions:
  //     You can combine functions to create new ones. For example, composing simpleIncrementer with another function to double the result.

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION X TYPES
  // we use val since its already built in

  // function with 2 args and string return type
  // val simpleConcatenate = new Function2[String, String, String]
  val simpleConcatenate = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  // Syntax sugars
  // val concatenate = (v1: String, v2: String) => v1 + v2
  // val increment = (x: Int) => x + 1

  // Higher order function:
  // - take funcs as arguments
  // - return funcs as results
  // For example: map() function maps a function passed within it as an argument to other arguments
  val aMappedList = List(1,2,3).map(x => x + 1) //anon func equal to  new Function1[Int, Int]
  println(aMappedList) // aMappedList is a new instance due to immutability

  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2 * x)) // flatmap takes in each x from list and returns list[x, 2x]
  println(aFlatMappedList)

  // alternative syntax:
  //val aFlatMappedList = List(1,2,3).flatMap { x =>
  //  List(x, 2 * x)
  //}

  val aFilteredList = List(1,2,3,4,5).filter(x => x <= 3)
  println(aFilteredList)

  //shorter syntax
  val aShorterFilteredList = List(1,2,3,4,5).filter(_ <= 3) // equivalent to (x => x <= 3)
  // repeating x twice is too much

  // since we have a new instance at the end of each function we can chain them

  // We can chain functions together for composites
  // all the pairs between nums 1 2 3 and letters a b c
  // [[1a,1b,1c],[2a,2b,2c],[3a,3b,3c]]
  val allPairs = List(1,2,3).flatMap(number => List('a','b','c').map(letter => s"$number$letter"))
  println(allPairs) // returns => List(1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c)

  // These chains become harder and harder to read in larger code bases
  // Hence scala uses for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a','b','c')
  } yield s"$number-$letter"
  //equivalent to the map flatmap chain
  // For comprehension helps deconstruct

  // COLLECTIONS:
  // Lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // [012345]
  val anExtendedList = 0 +: aList :+ 6 // [0123456]

  // Sequences
  val aSequence: Seq[Int] = Seq(1,2,3)
  // we can access elements at given indexes
  val accessedElement = aSequence(1) // element at index 1 = 2

  // Vectors: faster sequence implementation
  // vectors have fast access time but same methods as list and sequences
  val aVector = Vector(1,2,3,4,5)
  val firstVecElement = aVector.head
  val accessedVecElement = aVector(2)

  // Sets
  // main function is to check if a number contained within a set
  val aSet = Set(1,2,3,4,4,4,4,3,3,2,5) // Set(1,2,3,4,5) Order not important
  println(aSet)
  val setHas5 = aSet.contains(5) //true
  // add or remove elements with plus minus methods
  val addedSet = aSet + 6 // add num to set
  val removedSet = aSet - 3 // remove num form set

  // Ranges: Iteration create collections quickly
  val aRange = 1 to 1000 // fictitious, does not actually contain all nums within range
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,3,6...2000)
  println(twoByTwo)
  // toList, toSet, toSeq etc convert between any collections
  // toList can be called on any other collection to convert to List

  // Tuples
  // groups of values under the same value
  val aTuple = ("BonJovi", "Rock", 1982) //singular key value pair

  //maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 644654654),
    "Jane" -> 354654654 // ("Jane", 354654654)
  )

// Testing stuff
}
