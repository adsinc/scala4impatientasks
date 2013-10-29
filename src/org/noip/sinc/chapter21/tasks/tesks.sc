//5
{
	class Fraction(val num: Int, val dnm: Int) {

		def *(that: Fraction) = new Fraction(num * that.num, dnm * that.dnm)

		override def toString: String = "(%d/%d)".format(num, dnm)
	}

	object Fraction {
		def apply(num: Int, dnm: Int) = new Fraction(num, dnm)
	}

	class RichFraction(val from: Fraction) extends Ordered[Fraction] {
		def compare(that: Fraction): Int = from.num * that.dnm compare that.num * from.dnm
	}

	implicit def fractionToRichFraction(from: Fraction) = new RichFraction(from)

	def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if(a < b) a else b

	smaller(Fraction(1, 7), Fraction(2, 9))
}
//3
//import scala.language.implicitConversions
//import scala.language.postfixOps
//class FactorOps(val x: Int) {
//	def ! = 1 to x reduceLeft(_ * _)
//}
//implicit def int2FactorOps(x: Int) = new FactorOps(x)
//5!
//2
//class PercentOperations(val x: Int) {
//	def +%(percent: Int) = x + x * percent / 100
//}
//implicit def numericToPercentOperations(x: Int):  PercentOperations = new PercentOperations(x)
//120 +% 10
//1
//42 -> "Hello"
//"Hello" -> 42

