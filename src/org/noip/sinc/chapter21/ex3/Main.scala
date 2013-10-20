package org.noip.sinc.chapter21.ex3

import org.noip.sinc.chapter21.Fraction

/**
 * Created with IntelliJ IDEA.
 * Date: 19.10.13
 * Time: 22:28
 * @author dolgiy
 */
object Main extends App {
	import org.noip.sinc.chapter21.FractionConversions.fraction2Double
	val result = 3 * Fraction(3, 4)
	println(result)
	println(math.sqrt(Fraction(1, 4)))
}
