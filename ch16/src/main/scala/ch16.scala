object Chapter16 extends App {
  override def main(args: Array[String]) {
    // Lists differ from Arrays in two important ways: Lists are immutable and have a recursive structure (linked list) whereas arrays are flat.

    // Lists in scala are _covariant_ meaning that for each pair of types S and T, if S is a substype of T then List[S] is a subtype of List[T].
    // Because Nothing is a subtype of every other type, an empty list is a valid value for anything else
    val xs: List[String] = List()

    println(List(1,2)) // expands to...
    println(1 :: 2 :: Nil) // :: is the cons operator, ending with a : means it is an operation on the object on the right

    // On lists, unlike arrays, length is a relatively expensive operation. It needs to traverse the whole list to find its end and therefore takes time proportional to the number of elements in the list. That’s why it’s not a good idea to replace a test such as xs.isEmpty by xs.length == 0 . The result of the two tests are equivalent, but the second one is slower, in particular if the list xs is long.
    
    // _head_ and _tail_ have mirroring operations: _last_ and _init_, which return the last element in a list and everything except the last element in a list respectively
    // unlike _head_ and _tail_ these must traverse the entire length of the list to compute their result (O(n) rather than O(1) time)
    //
    // We _can_ randomly access elements in a list but it's not very popuplar because it takes linear time, unlike the constant time access of Arrays

    // drop and take are generalized versions of head/tail
    // xs splitAt n === (xs take n, xs drop n) // this avoids traversing the list twice
    val ks = List(1, 3, 5, 7, 9) 
    println(ks take 4) // List(1,3,5,7)
    println(ks drop 4) // List(9)
    println(ks splitAt 4) // (List(1,3,5,7), List(9))

    // random access is supported through the apply method - however this is less popular with lists (than arrays) because it takes linear time.  Under the hood apply is a combination of _drop_ and _head_
    ks apply 3 // == (xs drop 3).head

    // partition is like filter but returns two lists, one where the predicate returned true, one where it returned false
    ks partition (_ % 2 == 0) // (List(1, 3, 5, 7), List(9))

    // takeWhile and dropWhile also take a predicate and will take and drop the longest prefix until the predicate is satisfied
    val lessThan = (comparison: Int, n: Int) => n < comparison
    val lessThanSeven = lessThan(7, _: Int)
    ks takeWhile lessThanSeven // List(1, 3, 5)
    ks dropWhile lessThanSeven // List(7, 9)

    // span, like splitAt, combines operations, in this case takeWhile and dropWhile, preventing the need to traverse the list twice
    // unlike partition it will stop at the first predicate failure rather than applying the predicate to all elements of the list
    List(1, 2, 3, -4, 5) span (_ > 0) // (List(1, 2, 3), List(-4, 5))

    // forall and exists, awesome methods, the best, they're gonna be yuge
    // aka every and some
    val lessThanTen = lessThan(10, _:Int)
    ks forall lessThanTen // true
    (10 :: ks) forall lessThanTen // false

    val lessThanOne = lessThan(1, _:Int)
    ks exists lessThanOne // false
    (0 :: ks) exists lessThanOne // true

    // fold is a generalized version of reduce, allowing you to specify a starting value (rather than reduce using the first value of the collection) and a direction
    // =======Equivalent==========
    ks.foldLeft(0)(_ + _) // 25
    (0 /: ks) (_ + _)
    def sum(ys: List[Int]): Int = (0 /: ys)(_ + _)
    sum(ks)
    // =======Equivalent==========

    val numMap = Map("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)
    
    // useful for transforming one data structure to another
    // this works the same as numMap.values
    (List[Int]() /: numMap)((acc, num) => num._2 :: acc) // List(1,2,3,4)
    (numMap :\ List[Int]())((num, acc) => num._2 :: acc) // List(1,2,3,4)
  }
}
