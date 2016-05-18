object Chapter6 extends App {
// A rational number is a number that can be expressed as a ratio d n , where n and d are integers, except that d cannot be zero
  val half = new Rational(1, 2) // invokes toString
  val twoThirds = new Rational(2, 3)


  println(half + twoThirds)
  println(new Rational(5))
  println(twoThirds * new Rational(5, 10))
  println(twoThirds * 5)

  // Implicit conversion
  // teaches the Int class how to multiply by rational numbers -- must be in scope
  implicit def intToRational(x: Int) = new Rational(x)
  println(5 * twoThirds)
}

// n and d are _class parameters_ which will be gathered up and used to create a _primary constructor_ that takes the same two parameters
class Rational(n: Int, d: Int) {
  // preconditions are constraints on values passed into the constructor
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val num = n
  val denom = d
  
  def this(n: Int) = this(n, 1) // auxiliary constructor passes n on to the primary constructor

  // overwrite the default Object toString method with our own implementation
  override def toString: String = n + " / " + d

  private def gcd(a: Int, b:Int): Int = if (b == 0) a else gcd(b, a % b)
  private def simplify: Rational = new Rational(this.num / g, this.denom / g)

  def + (other: Rational): Rational = new Rational(num * other.denom + other.num * denom, denom * other.denom)

  def * (other: Rational): Rational = {
    val left = this.simplify
    // we can access simplify on other here because we're in the Rational class
    val right = other.simplify

    new Rational(left.num * right.num, left.denom * right.denom)
  }

  // _overloading_ the method simply means that the method name is now being used by multiple methods
  def * (other: Int): Rational = new Rational(this.num * other, this.denom)
}
