package com.rockthejvm

object PatternMatching extends App {

  // Switch expressions using pattern matching
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }

  // Pattern matching can deconstruct data structures into constituent parts
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => "T don't know what you are talking about"
  }

  // decomposing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "Unknown tuple"
  }

  // decomposing lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_,2,_) => "3 element list containing 2 on it's second position"
    case _ => "unknown list"
  }

  // PM will try all cases in sequence -> dont but _ at the start
  // if PM does not match anything it will throw a match error

}
