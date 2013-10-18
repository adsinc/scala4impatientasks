package org.noip.sinc.chapter17.tasks

//class Pair[T, S](val first: T, val second: S) {
//
//	def swap[T, S](p: Pair[T, S]) = new Pair(p.second, p.first)
//
////	override def toString: String = "[%s][%s]".format(first, second)
//}

class Pair[S, T](var first: S, var second: T) {
	def swap[T, S](p: Pair[T, T]) = {
		val tmp = p.first
		p.first = p.second
		p.second = tmp
		p
	}
	override def toString: String = "[%s][%s]".format(first, second)
}

object Task4 extends App {
	val p1 = new Pair(1, 2)
	val p2 = new Pair(2, 1)
	val p3 = new Pair("qw", "222")
	val p4 = new Pair(1, "222")
	println(p1.swap(p1))
	println(p1.swap(p2))
	println(p1.swap(p3))
}