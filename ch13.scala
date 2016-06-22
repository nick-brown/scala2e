// everything not explicity placed in a package is in the _unnamed_ package

// a package declaration at the beginning of the file will put the entire contents of the file into a package
// package com.scala2e.ch13 

// you can also wrap portions of code in curly braces to place it into a specific package
package com.scala2e.ch3 {
  object Chapter13 extends App {
    println("do stuff") // doesn't activate because packages aren't executed, obviously!
  }
}

// scala allows you to use short, unqualified names when accessing code that's in the same package
package launch {
  package navigation {
    class Navigator

    package tests {
      class NavigatorSuite {
        val nav = new Navigator // no need to use the fully qualified name
      }
    }
  }
}

package stuff {
  class Thing
}

package stuff {
  object OtherThings {
    val t = new stuff.Thing // can reference class Thing
  }
}

object Chapter13 extends App {
  println(stuff.OtherThings.t)
}
