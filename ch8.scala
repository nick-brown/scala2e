object Chapter8 extends App {
  // A function literal is compiled into a class that, when instantiated at runtime is a function value.  Thus the distinction between function literals and values is that function literals exist in the source code, whereas function values exist as objects at runtime.  The distinction is much like that between classes (source code) and objects (runtime)
  def doThing(thing: Int => Int) = thing(1)

  def addOne(x: Int): Int = x + 1
  //val addOne = (x: Int) => x + 1

  println(doThing(addOne))

  val lst = List(1, 2, 3, 4, 5)

  println(lst.map(x => x + 2))
  // _ can be used as a standin for whatever value is passed in
  println(lst.map(_ + 2))

  // Multiple underscores mean multiple parameters, not reuse of a single parameter repeatedly. The first underscore represents the first parameter, the second underscore the second parameter, the third underscore the third parameter, and so on.
  val f = (_: Int) + (_:Int)

  println(f(2, 3)) // 5

  // Partially applied function, _apply_ println function to arguments
  // use the underscore to stand in for an entire arguments list 
  lst.foreach(println _)

// This last form is allowed only in places where a function is required, such as the invocation of foreach in this example. The compiler knows a function is required in this case, because foreach requires that a function be passed as an argument. In situations where a function is not required, attempting to use this form will cause a compilation error.
  lst.foreach(println)

  def sum(x: Int, y: Int, z: Int): Int = x + y + z

  // can't pass this, it's trying to invoke the sum method
  // println(sum)

  // you can't assign a method or nested function to a variable or pass it as an argument to another function but you can do these things if you wrap the method or nrested function in a function value by placing an underscore after its name
  val sumPartial = sum _

  println(sumPartial) // <function3>

  // partially apply sum to its first two arguments, 3 and 5
  val sum3And5 = sum(3, 5, _:Int)

  println(sum3And5(1)) // 9

  // in this function literal lst is a _free_ _variable_ because the function literal itself does not give meaning to it.  The n variable, by contrast, is a _bound_ _variable_ because it does have a meaning in the context of the function
  // The function value (the object) that’s created at runtime from this function literal is called a closure. The name arises from the act of “closing” the function literal by "capturing" the bindings of its free variables. A function literal with no free variables, such as (x: Int) => x + 1 , is called a closed term, where a term is a bit of source code. Thus a function value created at runtime from this function literal is not a closure in the strictest sense, because (x: Int) => x + 1 is already closed as written. But any function literal with free variables, such as (x: Int) => x + more , is an open term. Therefore, any function value created at runtime from (x: Int) => x + more will by definition require that a binding for its free variable, more , be captured. The resulting function value, which will contain a reference to the captured more variable, is called a closure, therefore, because the function value is the end product of the act of closing the open term, (x: Int) => x + more
  val getFirstList = (n: Int) => lst(n)

  // will accept 0+ String arguments and turn it into an Array of the chosen type
  def echo(args: String*) = 
    for (arg <- args) println(arg)

  echo("one", "two")
  
  val arr = Array("one", "two")
  // echo(arr) // type mismatch
  echo(arr: _*) // expands the array elements to their own arguments, much like Python's splat

  def namedArgs(first: Int, second: Int): Unit = println(first, second)

  namedArgs(second=1, first=2) // we can also mix named and positional just like Python


  // tracing a tail-recursive function
  def boom(x: Int): Int = 
    if (x == 0) throw new Exception("kaboom!")
    else boom(x - 1)// the + 1 makes this non-tail-recursive, which we can see in the stack trace - removing the + 1 will shrink the stack

  boom(5)
}
