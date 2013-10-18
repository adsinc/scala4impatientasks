package ch11.task

import math.abs
/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 13.06.13
 * Time: 13:11
 */
class Money(d: Int, c: Int) {
	private val cent: Int = c % 100
	private val dollar: Int = d
	override def toString: String = "%d.%02d$".format(dollar, cent)
	def +(that: Money): Money = Money(dollar + that.dollar + (cent + that.cent) / 100, cent + that.cent)
	def -(that: Money): Money = if(this < that) {
		val t = that - this
		new Money(-t.dollar, t.cent)
	} else {
		new Money(dollar - that.dollar - (if(that.cent > cent) 1 else 0), cent - that.cent + (if(that.cent > cent) 100 else 0))
	}
	def ==(that: Money) = dollar == that.dollar && cent == that.cent
	def < (that: Money) = dollar < that.dollar || dollar == that.dollar && cent < that.cent
	def > (that: Money) = !(this < that)
}

object Money {
	def apply(d: Int, c: Int) = new Money(d, c)
}

object MoneyTest extends App {
	val m1 = Money(1, 75)
	val m2 = Money(0, 50)
	val m3 = Money(2, 25)
//	println(m1)
//	println(m2)
//	println(m3)
//	println(m1 + m2)
//	println(m1 + m2 == m3)
//	println(m1 < m2)
//	println(m2 < m1)
//	println(m1 > m2)
//	println(m2 > m1)
	println(m1 - m2)
	println(m2 - m1)
	println(m2 - m3)
	println(m3 - m2)
}