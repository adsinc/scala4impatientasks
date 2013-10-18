//17.8
class Pair[T](first: T, second: T) {
	def smaller(implicit ev: T <:< Ordered[T]) =
		if(first < second) first else second
}
def firstLast[A, C](it: C)(implicit ev: C <:< Iterable[A]) = (it.head, it.last)
firstLast(List(1, 2, 3))
//17.6
//import scala.reflect.ClassTag
//def makePair[T : ClassTag](first: T, second: T) = {
//	val r = new Array[T](2); r(0) = first; r(1) = second; r
//}
//makePair(4, 2)
//17.5
//class Pair[T : Ordering](val first: T, val second: T) {
//	def smaller(implicit ord: Ordering[T]) =
//		if(ord.compare(first, second) < 0) first else second
//}
//val p = new Pair(1, 2)
//p.smaller
//17.4
//class Pair[T <% Comparable[T]](val first: T, val second: T) {
//	def smaller = if(first.compareTo(second) < 0) first else second
//}
//val p = new Pair(1, 2)
//17.4
//class Pair[T](val first: T, val second: T) {
//	def replaceFirst[R >: T](newFirst: R) = new Pair(newFirst, second)
//}
//class Person
//class Student extends Person
//val studPair = new Pair(new Student, new Student)
//studPair.replaceFirst(new Person)
//17.3
//class Pair[T <: Comparable[T]](val first: T, val second: T) {
//	def smaller = if(first.compareTo(second) < 0) first else second
//}
//val p = new Pair("Fred", "Brooks")
//println(p.smaller)
//println((new Pair(1, 2)).smaller)
////17.1
//class Pair[T, S](val first: T, val second: S)
//val p1 = new Pair(1, "a")
//val p2 = new Pair[Any, Any](1, "a")
////17.2
//def getMiddle[T](a: Array[T]) = a(a.length /  2)
//getMiddle(Array("Mary", "had", "a", "little", "lamb"))
//val f = getMiddle[String]



