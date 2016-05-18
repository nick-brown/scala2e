object Chapter5 extends App {
  // Integral types
  val b: Byte = 5
  val s: Short = 5
  val i: Int = 5
  val l: Long = 5
  val c: Char = 5

  // Integral + Float & Double are called Numeric types
  val f: Float = 1 // 1.0
  val d: Double = 2.00 // 2.0

  // integer literals can be a _decimal_, _hexadecimal_, or _octal_
  
  // symbols
  println('asymbol)

  // all operators are just methods
  val nine = 4 + 5
  val 9 = (4).+(5)

  "HI".toLowerCase
  "HI" toLowerCase

  // logical-and (&&) and logical-or(||) are _short-circuited_ meaning they only evaluate as far as needed to determine the result
  def isValid: Boolean = true
  def isSaved: Boolean = false
  //def isValid(): Boolean = False
  val isSet: Boolean = isValid || isSaved // isSaved not evaluated because it isn't necessary
}
