object Chapter7 extends App {
  val files = (new java.io.File(".")).listFiles

  // generator
  for (file <- files) // type inference figures out that _file_ is a File because .listFiles returns an Array[File]
    println(file)

  for (n <- 1 to 4)
    println(n) // last number printed is 4 (inclusive)

  for (n <- 1 until 4)
    println(n) // last number printed is 3 (non-inclusive)

  for (n <- 1 to 10 if n % 2 == 0) // using a _filter_
    println(n) // print even #s

  for (file <- files if file.isFile if file.getName.endsWith(".scala")) // can use multiple filters
    println(file)

  def fileLines(file: java.io.File): List[String] = scala.io.Source.fromFile(file).getLines().toList

  def grep(pattern: String) = 
    for (
      file <- files
      if file.getName.endsWith(".scala");
      line <- fileLines(file); // nested loops
      // mid-stream assignment
      trimmed = line.trim // binds like a val
      if trimmed.matches(pattern)
    )
      println(file + ": " + trimmed)

  grep(".*6.*")

  def scalaFiles: Array[java.io.File] =
    for { // when this for expression completes it will return a collection that includes all of the files that pass the filter
      file <- files // the type of collection yielded is the same type of the collection we're iterating over
      if file.getName.endsWith(".scala")
    } yield file // Array[File] because _files_ is Array[File]

  for (f <- scalaFiles)
    println(f)

  def breakShit = throw new IllegalArgumentException

  import java.io.FileNotFoundException
  import java.io.IOException
  try {
    breakShit
  } catch {
    case ex: FileNotFoundException => println("handled FNF exception")
    case ex: IOException => println("handled IO exception")
    case ex: IllegalArgumentException => println("handled Illegal argument exception")
  } finally {
    // typical finally work, like closing a file
  }

  def doStuff = 
    try { // try/catch is an expression that yields a value
      breakShit
    } catch {
      case ex: IllegalArgumentException => "a value"
    }

  println(doStuff) // a value

  val firstArg = if (args.length > 0) args(0) else ""

  val complementaryDish = firstArg match { // results in a value
    case "salt" => "pepper"
    case "chips" => "salsa"
    case "eggs" => "bacon"
    case _ => "default"
  }

  println(complementaryDish)
}
