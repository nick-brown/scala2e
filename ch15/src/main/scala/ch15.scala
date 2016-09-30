// we could add _sealed_ before abstract to insure that only classes in this compilation unit are able to inherit from Expr.  This helps client programmers have confidence in the matches and also tells the compiler to flag missing combinations of patterns with a "match is not exhaustive" warning 
abstract class Expr

case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Chapter15 extends App {
  override def main(args: Array[String]) {
    // case classes
    //=============

    // 1. adds a factory method with the name of the class (no new keyword)
    val v = Var("thing")

    // 2. all arguments in the param list of a case class implicitly get a _val_ prefix, meaning they are maintained as fields
    println(v.name) // thing
    val op = BinOp("+", Number(1), Number(2))
    println(op.left) // Number(1.0)

    // 3. the compiler adds "natural" implementations of methods _toString_, _hashCode_, and _equals_ to your class - equals will compare a whole tree consiting of the class and (recursively) all its arguments.  Since == in Scala always delegates to _equals_, this means that elements of case classes are always compared structurally
    println(op) // BinOp(+,Number(1.0),Number(2.0))
    println(op.right == Number(2)) // true
    println(op.right == Number(3)) // false

    // 4. the compiler adds a _copy_ method to your class for making modified copies, which is useful for making a new instance of the class that is the same as another one except that one or two attributes are different.  The method works by using named and default parameters. You can specify the changes you'd like to make by using named params.  For any param you don't specify, the value from the old object is used.  As an example, here is how you can make an operation just like _op_ except that the operator has changed:
    println(op.copy(operator = "-")) // BinOp(-,Number(1.0),Number(2.0))

    // 5. the biggest bonus is that case classes support pattern matching
    def simplifyTop(expr: Expr): Expr = expr match {
      // the right side is a match expression which is like a switch but written after the selector
      // instead of switch (selector) { alternatives }
      // it's
      // selector match { alternatives }
      case UnOp("-", UnOp("-", e)) => e // Double negation
      case BinOp("+", e, Number(0)) => e // Adding zero
      case BinOp("*", e, Number(1)) => e // Multiplying by on
      case BinOp(op, left, right) => left
      case _ => expr
      // each pattern is tested for a match in order, and if it does match the part after the arrow is selected and executed
      // a _variable pattern_ like e matches every value.  The variable then referes to that value in the right hand side of the case clause.
      // the _wildcard pattern_ (_) also matches every value but does not introduce a variable name to refer to that value
      // lastly, a _constructor pattern_ shown in the free three match expressions does a deep comparison matching type _UnOp_ whose first argument matches the constant pattern "-" and whose second argument matches e
    }

    println(simplifyTop(UnOp("-", UnOp("-", Number(5))))) // Number(5.0) because it matches the first case

    println(simplifyTop(BinOp("+", Number(1), Number(2))))

    def matchHead(x: List[Int]) = x match {
      case List(0, _*) => x // _* will make this pattern match against a List of any length as long as it starts with 0
      case _ =>
    }

    println(matchHead(List(0, 1, 2)))
    println(matchHead(List(0, 1, 2, 3)))

    // Scala uses the _erasure_ model of generics, just like Java - meaning that no information about type arguments is maintained at runtime
    def isIntIntMap(x: Any) = x match {
      case m: Map[Int, Int] => true // will not work due to type erasure
      case _ => false
    }

    // both of these will return true
    isIntIntMap(Map(1 -> 1))
    isIntIntMap(Map("abc" -> "def"))

    // the only exception to this rule is the Array, which are maintained specially and can by pattern matched against
    def isArrayOfStrings(x: Any) = x match {
      case a: Array[String] => true
      case _ => false
    }

    println(isArrayOfStrings(Array("one", "two", "three")) ) // true
    println(isArrayOfStrings(Array(1, 2, 3))) // false

    // variable binding means the pattern check will be performed as normal and if it succeeds the expression after the @ symbol will be bound to the variable before the @ symbol
    // in this instance there would be no purpose to performing an absolute value operation twice, so the result of one can be used safely instead
    def variableBinding(x: Any) = x match {
      case UnOp("abs", e @ UnOp("abs", _)) => e
    } 
  }
}
