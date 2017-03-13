import scala.collection.mutable

object Chapter17 extends App {
  override def main(args: Array[String]) = {
    // linked-list that supports fast addition and removal of items to the beginning of the list but does not provide fast access to arbitrary indexes because the implementation must iterate through the list linearly
    // this works well for pattern matching
    val l = List(1, 2, 3)
    // access happens with parens (apply)
    println(l(2)) // 3

    // arrays allow you told hold a sequence of elements and efficiently access an element at an arbitrary position to either get or update, however you must know the size of the array up front
    val ints = new Array[Int](5)

    println(ints(2)) // 0

    // List provides fast access to the head of the list but not the end.  When you need to add to the end of the list you can build the list backwards by prepending elements to the front then reversing
    // alternatively you can use a _ListBuffer_, which is a mutable object which can help you build lists more efficiently when you need to append as it provides constant time append and prepend operations
    val lb = mutable.ListBuffer(1, 2, 3)
    lb += 4 // append
    0 +=: lb // prepend
    println(lb) // ListBuffer(0, 1, 2, 3, 4)

    // ArrayBuffers are like arrays but you can add and remove elements from the beginning or end of the sequence.  All array operations are available but may be slightly slower due to a layer of wrapping in the implementation.  The addition/removal operations are constant time on average and occasionally linear due to needing to allocate a new array to hold the buffer's contents
    val ab = mutable.ArrayBuffer[Int]()
    ab += 0
    ab += 1
    ab += (2, 3)

    println(ab) // ArrayBuffer(0, 1, 2, 3)

    // StringOps is the type that implements sequence methods on Strings.  _Predef_ has an implifict conversion from STring to StringOps so you can treat any string like a sequence
    def hasUpperCase(s: String) = s.exists(_.isUpper)
    println(hasUpperCase("Robert Frost")) // true

    // Sets will ensure that at most one of each object, as determined by ==, will be contained in the set at any one time
    // unique words in a sentence
    val words = "See Spot Run. Run Spot Run!".split("[ !,.]+").map(_.toLowerCase).toSet
    println(words)

    val s = Set(1,2,3)
    val t = Set(3,4,5)

    // add element(s)
    println(t + 6) // Set(3,4,5,6)
    println(t ++ List(6,7,8)) // Set(5, 6, 7, 3, 8, 4) order not preserved in sets

    // delete element(s)
    println(t - 5) // Set(3,4)
    println(t -- List(4,5)) // Set(3)

    // intersection
    println(s & t) // Set(3)

    val m = Map("i" -> 1, "ii" -> 2) 
    
    // add pair(s)
    println(m + ("iii" -> 3)) // Map("i" -> 1, "ii" -> 2, "iii" -> 3)
    println(m ++ List("iii" -> 3, "v" -> 5)) // Map("i" -> 1, "ii" -> 2, "iii" -> 3, "v" -> 5)

    // remove pair(s)
    println(m - "ii") // Map("i" -> 1)
    println(m -- List("i", "ii")) // Map()

    // access
    m("ii") // 2

    // Tuples are useful for passing around data of unlike types together and help prevent having to define small, data-heavy types just to group the data
    val tup = (1, "one", "i")

    // they can also be used to return multiple values from a method
    def wc(s: String) = {
      val chars = s.filterNot(_.isWhitespace).length
      val words = s.split("[ !,.]+").length
      (chars, words)
    }

    // parentheses are required to capture all of the values
    val str = "The quick brown fox jumps over the lazy log"
    val (chrs, wrds) = wc(str)
    println(chrs, wrds) // (35, 9)

    // this will give you multiple definition of the same value
    val cs, ws = wc(str)
    println(cs, ws) // ((35, 9), (35, 9))
  }
}
