package com.rockthejvm

object ObjectOrientation extends App {

  // classes and instances
  class Animal {
    //define fields
    val age: Int = 0
    // define methods
    def eat() = println("I'm eating")
  }

  val anAnimal = new Animal

  // inheritance: new classes can inherit methods and fields from existing classes
  // pass arguments to class using constructor argument
  class Dog(val name: String) extends Animal
  val aDog = new Dog("Lassie")
  // constructor arguments are NOT FIELDS
  // aDog.name does not exist
  // constructor arguments are ephemeral

  // However, class arguments can be promoted to field we wrap it as a val
  // Simply put val before argument

  // Polymorphism:
  // Declare an animal on the left
  // But construct a dog on the right
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime
  // at compile time compiler thinks we call eat from animal
  // but at runtime it calls from Dog - the derived class
  // if the Dog class chooses to override the eat() method

  // Abstract class:
  // Its when not all fields or methods in a class needs implementation
  // For example:
  //
  abstract class  WalkingAnimal {
    val hasLegs = true // all fields and methods are by default public
    // can restrict but adding private or protected modifiers
    // private - only this class has access to this method
    // protected - this class and descendants have access to this method

    def walk(): Unit // returns unit but has no implementation atm but can be used later
    // which ever class extends this abstract class will need to override
    // or provide the implementation
  }

  // Interface:
  // interface = ultimate abstract type in Java
  // means everything can be left unimplemented
  trait Carnivore {
    def eat(animal: Animal): Unit
    // takes an animal and returns a unit
  }

  // Method Naming
  trait Philospher {
    def ?!(thought: String): Unit
    // takes a string thought and returns a unit
    // ?! is a valid method name -> Scala is very permissive
    // we can now mix in the philosopher trait with the croc class

  }

  // Scala offers single class inheritance and multi trait inheritance
  // Multi trait inheritance = the subclass inherits directly from more than one class via traits.

  // if we define a class called Crocodile, it can extend animal but also mix in multiple traits.
  // we can extend as many traits as we want - multi trait inheritance (mix-in)
  // but only extend single animal class - single class inheritance

  // because we are mixing in trait that has an abstract method
  // we are forced to either implement this method eat()
  // or turn the class into an abstract method
  class Crocodile extends Animal with Carnivore with Philospher {
    // override - implement a method that's also present in a super type
    override def eat(animal: Animal): Unit = println("I am eating you, Animal")

    // we can also override a concrete method from animals
    override def eat(): Unit = super.eat() // provide another implementation for the eat method in animal

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")

  }

  // Scala method notations and method naming:
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  // above can also be written as:
  aCroc eat aDog
  // methods with single arguments can be written in in-fix notation
  // in-fix notation = object method argument (only methods with one arg)
  // makes it very intuitive to natural language: a croc eats a dog
  aCroc ?! "The dog was tasty."
  // ?! methods are used as operators
  // Operators are used as methods in Scala
  // the + operator is actually a method for the Int type


  // Anonymous classes:
  // Abstract classes and interfaces cannot be instantiated by myself
  // They need to be extended by a concrete class
  // We can have a concrete class that extends the interface Carnivore
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }
  // Im telling the compiler by creating value = new trait (creating anon class)
  // Create me a new class carnivore_anonymous_248y39 extends Carnivore {
  //    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  //    }
  // and instantiate this class for me:
  // so val dinosaur = new carnivore_anonymous_248y39


  // Scala does this via Singleton Object
  // mysingleton is the object instance of the mysingleton type
  object MySingleton {
    val mySpecialValue = 767
    def mySpecialMethod(): Int = 7677
    // Special method in scala: apply()
    // takes any args, present in any class, any object
    def apply(x: Int): Int = x + 1
  }


  MySingleton.mySpecialMethod()
  // Apply method allows instances of that class to call the applied method in the following way:
  MySingleton.apply(65)
  MySingleton(65)
  // Both above are valid
  // Does this mean each class / object can only have 1 apply method?
  // Essentially it allows instances of a class to be invoked as a function
  // we invoke mysingleton with the argument 65

  // Singletons: Companions
  // We can create a class and object of the same name
  // The class Animal and the object Animal are companions.
  object Animal { // companion object
    // companions can access each others private fields or methods
    // singleton animal above and instances of animal are different things
    val canLiveIndefinitely = false
  }
  val animalsCanLiveForever = Animal.canLiveIndefinitely
  // we access the canLiveIndefinitely field on the animal singleton object
  // in the same way you access static fields in Java or C++


  // Case classes are lightweight data structures with some boilerplate
  // When defining the case class person
  // the compiler generates
  // - auto implements equals() and hashcode() and copy() -> no need to write custom comparison logic
  // - sensible and quick serialization: bc we often send these instances over the wire in distributed systems
  //   for example with akka
  // - pattern matching (WIP)
  case class Person(name: String, age: Int)
  // they have built in companions with apply
  // makes case classes being constructed easily without the keyword new
  val bob = Person("Bob", 54) // similar to mysingleton invoking args

  // Exceptions:
  // Scala runs on the JVM so scala code is compiled to JVM bytecode
  // It can then be run Won every device that has JVM installed
  try {
    //code that can throw an exception
    val x: String = null
    x.length // cant get length from null empty
  } catch {
    case e: Exception => "error message"
  } finally {
    // execute some code always at the end - the stuff above are error and sanity checks
    // useful for closing connections or files or releasing resources
    // that would otherwise be dangerous to leave open
  }

  // Generics:
  // applicable to abstract classes, classes, traits
  // list is applicable to any type that we denote as T
  abstract class MyList[T] {
    // we can have definitions(functions) or values that depend on this type T
    def head: T // returns an element of type T
    // why not def head(): | no parenthesis for args?T
    def tail: MyList[T] // returns list of type T
    // when you use these definitions later the T becomes concrete
  }

  // Using a genetic with concrete type Int
  // List[T] == Lint[Int]
  val aList: List[Int] = List(1,2,3) // list.apply(1,2,3)
  val first = aList.head // whatever concrete type aList has becomes the generic type
  // hence first of type T becomes Int
  val rest = aList.tail
  // We are reusing the same functionality and applying to multiple types without needing to create functionality for each type

  // Point 1: In Scala we usually operate with IMMUTABLE VALUES
  // Any modification to an instance of a class should result (best practice) in another instance
  val reversedList = aList.reverse // returns a new list -> does not mutate the same list
  // Benefits:
  // 1) Works miracles in multithreaded/distributed environments
  // 2) Helps making sense about the code

  // Point 2: Scala is closest to the OOP ideal
}

