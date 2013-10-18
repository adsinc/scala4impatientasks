import scala.collection.GenSeq
import scala.math._
// 1
def values(fun: (Int) => Int, low: Int, high: Int) = (for(i <- low to high) yield (i, fun(i))).toArray
values(x => x * x, -5, 5)

// 2
val array = Array(0, -2, 2, 3, 10, 5, 2)
array.reduceLeft((a: Int, b: Int) => if(a > b) a else b)
// 3
def fact(n: Int) = (1 to n).reduceLeft(_ * _)
fact(5)
// 4
def fact1(n: Int) = (1 to n).foldLeft(1)(_ * _)
fact1(-5)
//5
def largest(fun: (Int) => Int, inputs: Seq[Int]) = inputs.map(fun(_)).max
largest(x => 10 * x - x * x, 1 to 10)
//6
def largestAt(fun: (Int) => Int, inputs: Seq[Int]) = inputs.maxBy(fun(_))
largestAt(x => 10 * x - x * x, 1 to 10)
//7
val pairs = (1 to 10) zip (11 to 20)
def adjustToPair(f: (Int, Int) => Int) = (pair: Pair[Int, Int]) => f(pair._1, pair._2)
adjustToPair(_ * _)((6, 7))
pairs.map(adjustToPair(_ + _))
//8
val strings = Array("***", "*", "**", "****")
val strings1 = Array("***", "*", "**", "****")
val lengths = Array(3, 1, 2, 4)
strings.corresponds(lengths)(_.length == _)
9
def corresponds1[A, B](a: GenSeq[A], b: GenSeq[B], p: (A,B) => Boolean): Boolean = {
	val i = a.iterator
	val j = b.iterator
	while (i.hasNext && j.hasNext)
		if (!p(i.next, j.next))
			return false

	!i.hasNext && !j.hasNext
}
corresponds1(strings, strings1, _.length + _ )
def unless(condition: => Boolean)(block: => Unit) { if(!condition) block }
unless(1 > 2) {
	println("1 < 2")
}