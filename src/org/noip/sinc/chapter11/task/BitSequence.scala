package ch11.task

import math._
/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 14.06.13
 * Time: 13:57
 */
class BitSequence {
	var seq: Long = 0

	override def toString: String = "%64s".format(seq.toBinaryString).replace(" ", "0")

	def update(bit: Int, state: Int) = seq |= (state & 1L) << bit % 64
	def apply(bit: Int): Int = if ((seq & 1L << bit % 64) > 0) 1 else 0
}

object BitSequenceTest extends App {
	val b1 = new BitSequence
	b1(1) = 1
	val a = b1(1)
	println(a)
	b1(63) = 1
	b1(8) = 1
	println(b1)
}