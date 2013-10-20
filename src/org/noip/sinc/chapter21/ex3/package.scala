package org.noip.sinc.chapter21

/**
 * Created with IntelliJ IDEA.
 * Date: 19.10.13
 * Time: 22:30
 * @author dolgiy
 */
object FractionConversions {
	implicit def int2Fraction(n: Int) = Fraction(n, 1)
	implicit def fraction2Double(f: Fraction) = f.num * 1.0 / f.dnm
}
