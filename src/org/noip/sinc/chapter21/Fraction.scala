package org.noip.sinc.chapter21

/**
 * Created with IntelliJ IDEA.
 * Date: 19.10.13
 * Time: 22:32
 * @author dolgiy
 */
class Fraction(val num: Int, val dnm: Int) {
	def *(that: Fraction) = new Fraction(num * that.num, dnm * that.dnm)

	override def toString: String = "(%d/%d)".format(num, dnm)
}
object Fraction {
	def apply(num: Int, dnm: Int) = new Fraction(num, dnm)
}