/**
 * Created with IntelliJ IDEA.
 * Date: 19.10.13
 * Time: 14:36
 * @author dolgiy
 */
//21.1
class Fraction(val num: Int, val dnm: Int) {
	def *(that: Fraction) = new Fraction(num * that.num, dnm * that.dnm)

	override def toString: String = "(%d/%d)".format(num, dnm)
}
object Fraction {
	def apply(num: Int, dnm: Int) = new Fraction(num, dnm)
}
implicit def int2Fraction(n: Int) = Fraction(n, 1)
val result = 3 * Fraction(4, 5)
