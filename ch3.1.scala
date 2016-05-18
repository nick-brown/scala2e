import scala.io.Source

object Chapter3dot1 extends App {
  val lines = Source.fromFile(args(0)).getLines().toList
  val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
  def widthOfLength(str: String): Int = str.length
  
  if (args.length > 0) {
    for (line <- lines) {
      val diff = widthOfLength(longestLine.length.toString) - widthOfLength(line.length.toString)
      println(line.length + (" " * diff) + " | " + line)
    }
  } else 
    Console.err.println("Please enter filename")
}
