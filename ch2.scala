object Chapter2 extends App {
  val two = 1 + 1  
  var three = 1 + 2

  three = 3 // works because vars can be re-assigned
  println(three) // 3
  // two = 2 // error, vals have immutable REFERENCES, meaning it can not be re-assigned - however the value can be mutated (in the case of a collection)

  val msg: java.lang.String = "my string goes here" // use java types
  val msg2: String = "my string goes here" // use simple type name (maps to Java type)
  val msg3 = "my string goes here" // use type inference

  def max(x:Int, y:Int): Int = { // the final Int is the result type, akin to Java's return type, this is only required in some circumstances, such as recursion
    if(x > y) x // scala will automatically return the last line
    else y
  }

  // can also be written
  def max2(x:Int, y:Int): Int = if(x > y) x else y

  // function that takes no params and returns no interesting result
  def greet() = println("hello world")
  // greet: ()Unit
  // Unit is the function's result type, similar to Java's void meaning the function returns no interesting result and is therefore only executed for its side effect

  // args keyword for arguments given from the command line
  println(args(0)) // notice array access is done with parens, this is because it's an actual method call

  // imperative looping
  args.foreach(arg => println(arg))
  // with type
  // args.foreach((arg: String) => println(arg))

  // shorthand available if there is only one argument and it should be passed to a function
  args.foreach(println)

  val addTwo = (x:Int) => x + 2 // this is a function literal assigned to a value

  for(arg <- args) // the <- can be pronounced "in"
    println(arg)
}

// alternate way of creating an entry point to the application
// object Chapter2 {
//   def main(args: Array[String]): Unit = {
//     println("hello world")
//   }
// }
