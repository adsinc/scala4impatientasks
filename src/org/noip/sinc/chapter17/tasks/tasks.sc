//10


//8
//class Pair[+T](var first: T, var second: T) {
//	def replaceFirst[R >: T](newFirst: R) { first = second }
//}
//class Person
//class Student extends Person
//val s1 = new Student









//val s2 = new Student
//val pers = new Person
//val pair = new Pair(s1, s2)
//pair.replaceFirst(pers)
//6
//def middle[T](i: Iterable[T]) = {
//	i.drop(i.size / 2).iterator.next()
//}
//middle("World")
//3
//class Pair[T, S](val first: T, val second: S) {
//
//	def swap[T, S](p: Pair[T, S]) = new Pair(p.second, p.first)
//
//	override def toString: String = "[%s][%s]".format(first, second)
//}
//val p1 = new Pair(1, 2.0)
//val p2 = new Pair("s", 1)
//val p3 = new Pair("qw", 2.1)
//p1.swap(p1)
//p1.swap(p2)
//p1.swap(p3)
//2
//class Pair[T](var first: T, var second: T) {
//	def swap = {
//		val tmp = first
//		first = second
//		second = tmp
//		this
//	}
//	override def toString: String = "[%s][%s]".format(first, second)
//}
//val p1 = new Pair(1, 2)
//p1.swap
//val p2 = new Pair("qqq", "str")
//p2.swap
//1
//class Pair[T, S](val first: T, val second: S) {
//	def swap = new Pair(second, first)
//}
//val p1 = new Pair(1, 2)
//p1.swap
//val p2 = new Pair(3, "str")
//p2.swap