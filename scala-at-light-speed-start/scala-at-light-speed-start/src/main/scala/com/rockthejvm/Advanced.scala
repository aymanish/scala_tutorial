package com.rockthejvm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object Advanced extends App {

  // Lazy evaluation - expression is not evaluated until first used
  /*
  They delay computation until the value is actually needed.
  This can be both a performance optimization and a way to
  avoid unnecessary computations, especially when dealing
  with expensive operations or resources that may not be used.
   */
  lazy val aLazyValue = 2
  // 2 is not associated with the lazy val until first used
  lazy val lazyValueWithSIdeEffect = {
    // set up code block with curly braces
    println(("I am so very lazy"))
    43
  }
  // above lazy expression prints out nothing
  // but without lazy the expressions are evaluated and attributed to the val name
  // code below prints output
  val ValueWithSIdeEffect = {
    println(("I am so very lazy"))
    43
  }

  // Lazy values are only evaluated when first used
  val eagerValue = lazyValueWithSIdeEffect + 1
  // now the codeblock is run

  // lazy values are useful in infinite collections and other rare use cases

  // Pseudo Collections: Option type / Try type
  // these types are useful in usecases in large code bases when you hve unsafe methods
  // normally to defend against null methods we need to implement something
  def methodWhichCanReturnNull(): String = "hello. Scala"
  if (methodWhichCanReturnNull() == null) {
    // defensive code against null
  }
  def methodWhichCanReturnNullScala(): String = "hello, Scala"
  val anOption = Option(methodWhichCanReturnNullScala()) // Some("hello Scala) -> sub-type of the option type
  // option = collection which contains at most one element: Some(value) or None
  // kind of acting as a wrapper

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }
  // other code don't have such null checks

  // Try
  def methodWhichCanThrowException(): String = throw new RuntimeException
  // normally we would use try catch finally but too many layers makes it complex
  val aTry = Try(methodWhichCanThrowException())
  // try: collection with either a value if code went well or an exception

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(exception) => s"I have obtained an exception: $exception"
  }
  // Both try and option can work with map / flatmap / filter

  // Evaluate something no another thread: Asynchronous programming
  // Pseudo collection: Future
  // we put a code-block within the future constructor
  val aFuture = Future {
    println(("Loading..."))
    Thread.sleep(3000)
    println(("I have computed a value"))
    67
  }
  // future: collection which contains a value when its evaluated
  //         until then it does not contain any values
  // future is composable with map flatmap and filter etc

  /*

Let’s break down how to use Future in Scala in a simple and intuitive way. I’ll guide you step-by-step, so by the end, you'll know how to use Future to handle asynchronous programming in Scala.

1. What is a Future?
In Scala, a Future is an object that represents a computation that will complete at some point in the future. It may:

Complete successfully with a result.
Fail with an error.
Still be running.
Think of it like ordering food online: you order it (start the computation), and later the food (result) will arrive. While you're waiting, you can do other things.

2. Creating a Simple Future
Here’s how to create a simple Future in Scala:

scala
Copy code
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val futureResult = Future {
  // This block runs in the background (asynchronously)
  Thread.sleep(3000)  // Simulate a slow computation (e.g., a network request)
  "Data from server"  // This will be the result of the Future
}

println("Fetching data asynchronously...")  // Printed immediately
Future { ... }: The code inside the curly braces runs asynchronously (in the background).
Thread.sleep(3000): Simulates a delay (e.g., a slow network request or long computation).
The result ("Data from server") will be available in the future (after 3 seconds).
3. Handling the Result of a Future
You don’t block your program waiting for the Future to complete. Instead, you specify what should happen when the Future completes using callbacks like map, flatMap, or onComplete.

Example with map:
You use map to specify what to do once the Future completes:

scala
Copy code
futureResult.map(result => println(s"Received: $result"))
map: This method takes a function that will be applied to the result of the Future once it’s available. In this case, it will print the result when the Future completes.
Here’s the full example:

scala
Copy code
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val futureResult = Future {
  Thread.sleep(3000)
  "Data from server"
}

futureResult.map(result => println(s"Received: $result"))

println("Fetching data asynchronously...")  // Printed immediately
Explanation:

The program prints "Fetching data asynchronously..." right away.
The Future runs in the background.
After 3 seconds, "Received: Data from server" is printed when the Future completes.
   */
  val futureResult = Future {
    Thread.sleep(3000)
    "Data from server"
  }

  futureResult.map(result => println(s"Received: $result"))

  println("Fetching data asynchronously...")  // Printed immediately

  // Sometimes, the Future might fail (e.g., a network error).
  // To handle both success and failure, you can use onComplete.
  futureResult.onComplete {
    case Success(result) => println(s"Success: $result")
    case Failure(exception) => println(s"Failed with: ${exception.getMessage}")
  }

  // This will wait for the future to complete before exiting the program
  Await.result(futureResult, 5.seconds)

  // Pseudo collections:
  // Future try and option types are called monads

  // Implicits:
  // Powerful features of Scala compilers

  // 1) Implicit arguments
  // if we define a method with implicit args
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  // and then if I define an implicit val
  implicit val myImplicitInt = 46
  println((aMethodWithImplicitArgs))
  // we can pass the myImplicitInt to the implicit method above without writing any arguments
  // above prints 47 (46 + 1)
  // compiler tries to find an implicit arg

  // 2) Implicit conversions:
  // usually we do implicit conversions to add methods to existing types
  // over which we don't have any control over the code

  // define an implicit class
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0 // returns true if num is even
  }

  // we can do this as usual
  val richInt = new MyRichInteger(8)
  println(richInt.isEven())

  // but we can also do this
  println(8.isEven())
  // auto detects implicit class since 8 is an int and the class is also an int with the isEven() method
  // compiler is smart enough to do new MyRichInteger(23).isEven
  // use implicits with care

}
