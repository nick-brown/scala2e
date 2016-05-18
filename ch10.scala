object Chapter10 extends App {
  // Composition: one class holds a reference to another - using the referenced class to help fulfill its mission (this is the case of Array[String] in our Element class below
  // _FAVOR COMPOSITION OVER INHERITANCE_
  // Inheritance is the superclass/subclass relationship
  
  // abstract means that methods may have no implementation, meaning you can not instantiate an abstract class
  abstract class Element {
    // a method is abstract if it does not have an implementation, meaning an abstract keyword is not necessary for contents
    def contents: Array[String] // _declares_ the abstract methods contents but _defines_ no other concrete methods
    // these are _parameterless_ methods
    def height: Int = contents.length
    def width: Int = if (height == 0) 0 else contents(0).length

    def above(that: Element): Element = new ArrayElement(this.contents ++ that.contents)

    // this is an _empty-paren_ method
    // def width(): Int
    
    // THE RECOMMENDED CONVENTION IS TO USE A PARAMETERLESS METHOD WHENEVER THERE ARE NO PARAMS _AND_ THE METHOD ACCESSES MUTABLE STATE ONLY BY READING FIELDS OF THE CONTAINING OBJECT (IN PARTICULAR IT DOES NOT CHANGE MUTABLE STATE).  THIS CONVENTION SUPPORTS THE _UNIFORM ACCESS PRINCIPLE_ WHICH SAYS THAT CLIENT CODE SHOULD NOT BE AFFECTED BY A DECISION TO IMPLEMENT AN ATTRIBUTE AS A FIELD OR A METHOD
    // For instance, we could have simply chosen to implement width and height as fields instead of methods by simply chaing _def_ to _val_
  }

  // new Element // compiler error: class Element is abstract
  
  class ArrayElement(conts: Array[String]) extends Element {
    def contents: Array[String] = conts // _Implements_ a concrete contents method to fulfill the requirement of the abstract contents declaration in Element

    // the _final_ modifier prevents demo from being overwritten
    final def demo() {
      println("do stuff")
    }
  }

  // Scala has two namespaces in place of Java's 4
  // Java: fields, methods, types, and packages
  // Scala: types (class and trait names) and values (fields, methods, packages, singleton objects)
  
  // Parametric field - reduce redundancy code smell by creating a val directly out of a parameter
  // can use access modifiers such as private and override
  class ArrayE(val contents: Array[String]) extends Element {
    // no need to def contents here
  }

  // Invoking superclass constructors
  class LineElement(s: String) extends ArrayElement(Array(s)) {
    // override needed to redefine concrete members of a superclass
    override def height: Int = 1
    override def width: Int = s.length
  }

  // a requirement of type Element could also be fulfilled by subclasses ArrayElement and LineElement -- this is cold polymorphism (many shapes, many forms)
  val el1: Element = new ArrayElement(Array("some text", "some more text"))

  // method invocations on variables and expressions are _dynamically bound_, meaning that the actual method being invoked is determined at runtime based on the class of the _object_, NOT the type of the variable/expression.

  // final prevents FakeElement from being sublcassed
  final class FakeElement extends LineElement("") {

  }

  // Prefer composition to to inheritance if your primary goal is code reuse.  Inheritance suffers from the fragile base class problem in which you can inadvertantly break subclasses by changing a superclass
  // As yourself if the relationship is best described by _is-a_.  For example, it would be reasonable to say that ArrayElement _is-an_ Element.  Another question to ask is whether clients will wan tto use the sublcass type _as_ the superclass type (polymorphism).
}
