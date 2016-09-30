class Thing {
  // basic class definition - include _fields_ and _methods_ here which are collectively called _members_

  // Fields aka instance variables (hold state)
  val num: Int = 1
  var aNum: Int = 5

  private val sum = 0 // can only be accessed by methods defined in this class

  // Methods (do computation)
  def doStuff(): Unit = {
    println("side effects go here")
  }

  // leaving off the = sign make sthe Scala compiler force the return type as Unit, losing any return value in the process
  def doMoreStuff() { aNum = 10 } // implicit return type of Unit
}

// when a singleton object shares the same name with a class it's called a _companion object_ and they must be defined in the same source file
// they can access each other's private members *giggle*
object Thing { // singletons can't take parameters because you can't "new" them
  // companion objects are a good place to define static methods
}

object Chapter4 extends App { // App is a trait that declares a main with the proper signature and then executes all code within this Singleton object
  // basic instantiation
  val t = new Thing
  // methods like println and assert are implicity imported from the package _Predef_ located in _scala_
  println(t) // Thing@xxxxxxxx
  t.doStuff
}

object Chap4 {
  def main(args: Array[String]) { // main has advantages such as being able to access command line arguments and is the only option if you want your program to be multi-threaded

  }
}
