
/**
 * Created with IntelliJ IDEA.
 * Date: 19.10.13
 * Time: 14:36
 * @author dolgiy
 */
//21.7
//class Pair[T: Ordering](val first: T, val second: T) {
//	def smaller(implicit ord: Ordering[T]) =
//		if(ord.compare(first, second) < 0) first else second
//}
//Pair(40, 2)
//class Pair[T: Ordering](val first: T, val second: T) {
//	def smaller =
//		if(implicitly[Ordering[T]].compare(first, second) < 0) first
//		else second
//}
class Pair[T: Ordering](val first: T, val second: T) {
	def smaller = {
		import Ordered.orderingToOrdered
		if(first < second) first else second
	}
}
//21.6
//def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if(a < b) a else b
//smaller(2, 20)
//smaller("Hello", "World")
//21.5
//case class Delimiters(left: String, right: String)
//object FrenchPunctuation {
//	implicit val quoteDelimiters = new Delimiters("{", "}")
//}
//def quote(what: String)(implicit delims: Delimiters) =
//	delims.left + what + delims.right
//quote("Hello")(Delimiters("<<", ">>"))
//import FrenchPunctuation.quoteDelimiters
//quote("Hello")
//21.2
//class RichFile(val from: File) {
//	def read = Source.fromFile(from).mkString
//}
//implicit def file2RichFile(from: File) = new RichFile(from)
//val contents = new File("README.md").read
//21.1
//class Fraction(val num: Int, val dnm: Int) {
//	def *(that: Fraction) = new Fraction(num * that.num, dnm * that.dnm)
//
//	override def toString: String = "(%d/%d)".format(num, dnm)
//}
//object Fraction {
//	def apply(num: Int, dnm: Int) = new Fraction(num, dnm)
//}
//implicit def int2Fraction(n: Int) = Fraction(n, 1)
//val result = 3 * Fraction(4, 5)
