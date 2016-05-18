object Chapter12 extends App {
  // Traits encapsulate method and field definitions which can be reused by mixing them into classes
  val kermit = new Frog
  kermit.philo()

  // traits also define types
  val prince: Philosophical = new Frog

  val mq = new MyQueue
  mq.put(3)
  mq.put(2)
  mq.put(1)

  println(mq.get) // 7
  println(mq.get) // 5
  println(mq.get) // 3
}

class Animal

// if you wish to explicity extend a superclass and also mix in a trait you can use _extends_ for the superclass and _with_ for traits
class Frog extends Animal with Philosophical with HasLegs { // once a trait is mixed into class you can alternatively call it a mixin
  override def toString = "green"
}

// traits are like classes except that can not have any class parameters and that their super calls are dynamically bound as opposed to the statically bound calls of classes 
trait Philosophical {
  def philo() {
    println(super.toString) // "super.toString" will determine what super is _each time_ it is mixed in to a concrete class
    println("I consume memory, therefore I am!")
  }
}

trait HasLegs

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

// STACKABLE MODIFIERS TO A METHOD
// using extends on a Trait means that only classes that sublcass IntQue can use Doubling
trait Doubling extends IntQueue {
  // normally calling super wouldn't work but because the call in a trait is _dynamicall bound_ this method will succed so long as the trait is mixed in _after_ another trait or class that gives a concrete definition to the method
  abstract override def put(x: Int) { super.put(2 * x) }
  // ^^^^^^^^^^^^^^ means that the trait must be mixed into some class that has a concrete definition of the method in question
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait OverFive extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

// class Thing2 with Doubling // compile error

// the modified put methods from the traits stack here -- right to left
class MyQueue extends BasicIntQueue with OverFive with Incrementing with Doubling

// how is this different than multiple inheritance?  With multiple inheritance _super_ is defined by where it is called, but wiht traits the method called is determined by a _linearization_ of the classes and traits that are mixed into a class.  This enables the stacking of modifications described in the previous section
