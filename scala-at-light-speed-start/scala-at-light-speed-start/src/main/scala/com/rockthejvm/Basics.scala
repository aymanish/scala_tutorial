package com.rockthejvm

object Basics extends App {

  // Defining values:
  // values act as constants similar to Java
  // they cannot be reassigned
  // Hence, in Scala, we compose values to obtain new values
  // val value_name: value_type = value
  val meaningOfLife: Int = 42
  // Compiler detects value_types so do not always need to define
  val aBoolean = false // type is optional

  // Has other data types such as Int, Bool, Double, Floats, Char, Strings

  // Strings and string operations:
  val aString = "I love Scala"
  // Strings can also be concatenated with +
  val aComposedString = "I" + " love" + " Scala"
  // In Scala, we also have interpolation, similar to Python F strings
  val aInterpolatedString = s"The meaning of life is $meaningOfLife"
  println(aString, aComposedString)

  // Expressions:
  // expression = structure that can be reduced to a value
  val anExpression = 2 + 3

  // In Scala we have to think in terms of values to obtain new values
  // Everything is an expression that can be reduced to a value
  val ifExpression = if (meaningOfLife > 43) 56 else 999
  // These expressions can also be chained
  val chainedIfExpression =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  // We are assigning values based on conditions instead of procedural work

  // Code Blocks:
  // Code blocks are a form of expression in Scala
  val aCodedBlock = {
    // add definitions
    // define values, functions, classes, inner codeblocks, anything really
    val aLocalValue = 67

    // but need to return something at the end
    // the last expression is the value of the entire code block
    aLocalValue + 3 + meaningOfLife
  }

  // Functions:
  // defining functions
  // def name_function(arg1: agr1_type, arg2: arg2_type): return_type = expression
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // Functions are usually recursive in practice
  def factorial(n: Int): Int =
    // 4 = 4 x 3 x 2 x 1
    if (n <= 1) 1 // return condition when n = 1
    else n * factorial(n-1) // recursive calls

  // In Scala we don't use loops or iteration
  // We use RECURSION!


  // Unit Types:
  // The unit type = no meaningful value
  // Equivalent to void
  // Doesnt return something but just does something
  // Forexample wehn printing it doenst return but just prints action
  // Known as the type of SIDE EFFECTS
  // printing something, storing something, retreiving something
  // nothing to do with actual computation
  val theUnit = ()





}
