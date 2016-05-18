object Chapter3 extends App {
  // when you instantiate an object in Scala you can parameterize it with values and types, meaning you can configure the instance by passing objects to a constructor in parentheses
  val big = new java.math.BigInteger("12345")
  println(big.getClass) // class java.math.BigInteger

  // parameterize an array of strings to a length of 3 (all null)
  // note the array access with parens instead of square brackets
  // this is because we're actually calling Array[String].apply(3)
  //==================================================================
  // This principle is not restricted to arrays: any application of an object to some arguments in parentheses will be transformed to an apply method call.
  //==================================================================
  val strings = new Array[String](3) // the long way, using new and giving the contained type (String)

  for(idx <- 0 to 2) // to to create a range
    println(strings(idx))

  // because operators are simply methods on objects, we could also write
  for(idx <- (0).to(2))
    println(strings(idx))

  var three = 1 + 2
  // could also be rewritten as...
  three = (1).+(2)

  // in the same way that using parens will be transformed to an apply method call
  // setting a value using = will be transformed into an update call
  strings(1) = "Hi"
  strings.update(1, "Hi")

  var numNames = Array("zero", "one", "two") // call a (static) factory method named apply on the Array class
  numNames = Array.apply("zero", "one", "two")

  // use lists for immutability
  val numbers = List(1, 2, 3)
  val moreNumbers = List(4, 5, 6)

  // ::: concatenates two lists
  println(numbers ::: moreNumbers)

  println(0 :: numbers) // :: is 'cons', which prepends a list with a value
  // :: is a method of the _right_ operand, which is unlike other operators where the method is of the left operand - this is true of all operators that end with a colon

  // an easy way to create a new list is to cons the values, ending with Nil, which is necessary because the last value does not have a cons method
  val nums = 1 :: 2 :: 3 :: Nil 
  println(nums) // List(1, 2, 3)

  // tuples can contain elements of different types
  val pair = (99, "Luftballons")
  println(pair.getClass) // class scala.Tuple2

  val triple = Tuple3(99, "Luftballons", "Goldfinger")

  // accessing an element in a tuple is different than a list because 'apply' always returns the same type, and a tuple can contain elements of varying types (also 1-indexed)
  println(triple._2) // Luftballons

  // immutable set initialization
  var jetSet = Set("Boeing", "Airbus")
  // an immutable set will implement + differently than a mutable set - it will return a new set with the new value whereas the mutable version will simply alter the existing set
  jetSet += "Lear"

  println(jetSet) // Set(Boeing, Airbus, Lear)

  // mutable set initialization
  import scala.collection.mutable.Set

  val movieSet = Set("Hitch", "Poltergeist")
  movieSet += "Shrek" // this actually mutates the set
  movieSet.+=("Shrek 2") // again, operators are just instance methods

  println(movieSet) // Set(Poltergeist, Shrek, Hitch)


  import scala.collection.mutable.Map

  val treasureMap = Map[Int, String]() // the type parameterization is necessary because we're passing nothing to the factory function
  treasureMap += (1 -> "Go to island") // 1 -> "Go to island" becomes...
  treasureMap += (2 -> "Dig at X") // 1.->("Go to island") which returns a two element tuple containing the key and the value

  println(movieSet.mkString("\n")) // works like .join in javascript
}
