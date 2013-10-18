package ch11.task

import math.abs
/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 13.06.13
 * Time: 13:11
 */
class Fraction(n: Int, d: Int) {
	private val num: Int = if(d == 0) 1 else n * sign(d) / gcd(n, d)
	private val den: Int = if(d == 0) 1 else d * sign(d) / gcd(n, d)
	override def toString: String = num + "/" + den
	def sign(a: Int) = if(a > 0) 1 else if(a < 0) -1 else 0
	def gcd(a: Int, b: Int): Int = if(b == 0) abs(a) else gcd(b, a % b)
	def +(that: Fraction): Fraction = {
		if(this.den == that.den) new Fraction(this.num + that.num, this.den)
		else new Fraction(this.num * that.den + that.num * this.den, this.den * that.den)
	}
	def -(that: Fraction): Fraction = {
		if(this.den == that.den) new Fraction(this.num - that.num, this.den)
		else new Fraction(this.num * that.den - that.num * this.den, this.den * that.den)
	}
	def *(that: Fraction): Fraction = new Fraction(this.num * that.num, this.den * that.den)
	def /(that: Fraction): Fraction = new Fraction(this.num * that.den, this.den * that.num)
}

object FractionTest extends App {
	val f1 = new Fraction(1, 3)
	val f2 = new Fraction(3, 5)
	val m1 = f1 * f1 * f1
	println(m1)
	val m2 = m1 * f1
	println(m2)
	println(f1 / f2)
	println(f1 + f1)
	println(f1 + f2)
	println(f1 - f2)
}