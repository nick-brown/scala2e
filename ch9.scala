import scala.io.Source
import java.io.File

object Chapter9 extends App {
  FileMatcher.filesEnding(".scala").foreach(println)

  // exists is a control/looping abstraction provided by the Scala _library_, whereas while and for are provided by the Scala language
  def containsNegativeNum(nums: List[Int]) = nums.exists(_ < 0)

  println(containsNegativeNum(List(1, 2, 3))) // false

  def sum(x: Int, y: Int): Int = x + y
  
  // currying can be thought of as a single function applied to multiple lists of arguments
  def curriedSum(x: Int)(y: Int): Int = x + y

  println(curriedSum(1)(2))

  // this could also be thought of as this form returning a function literal
  def curriedSum2(x: Int) = (y: Int) => x + y

  // we can get ahold of curriedSum's "second" function by using the placeholder notation to use curriedSum in a partially applied function expression
  val onePlus = curriedSum(1)_
  println(onePlus) // <function1> (anonymous function of arity 1)


  // how about more than two arguments?
  def sumThree(x: Int)(y: Int)(z: Int) = x + y + z

  // this works because _ is a placeholder for the _entire_ remaining list of arguments, not just a single parameter
  var addThreeAndFive = sumThree(5)_

  println(addThreeAndFive(3)(1))

  // argument signature for a function that takes a function
  def twice(fn: Int => Unit, param: Int) = { 
    fn(param)
    fn(param) 
  }

  twice(println, 5)

  def aboveFive(x: Int) = x > 5

  // we can use curly braces for an invocation that only takes a single paramter to make it look/feel more like a control structure 
  aboveFive { 6 } // true

  // this can also be done with curried functions
  def doWithFile(file: String)(fn: String => Unit) {
    for (line <- Source.fromFile(file).getLines)
      fn(line)
  }

  // feels more like a control structure to client programmers
  doWithFile("ch8.scala") {
    println // prints the whole contents of the ch8 file
  }

  // by name parameter for predicate
  def myAssert(predicate: => Boolean) = {
    println(predicate)
    if (!predicate)
      throw new AssertionError
  }

  // in this case 5 > 3 not evaluated until after it's passed
  // myAssert(() => 5 > 3)

  //
}

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) = {
    for (file <- filesHere; if matcher(file.getName))
    yield file
  }

  def filesEnding(query: String) = 
    filesMatching(x => x.endsWith(query)) // this is the long form function literal

  def filesContaining(query: String) =
    filesMatching(_.contains(query)) // this is the short form function literal

  def filesRegex(query: String) =
    filesMatching(_.matches(query))
}
