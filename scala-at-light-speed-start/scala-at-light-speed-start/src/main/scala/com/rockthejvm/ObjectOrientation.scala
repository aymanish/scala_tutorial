package com.rockthejvm

class ObjectOrientation extends App {

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


  // Scala does this vis Singleton Object









}
