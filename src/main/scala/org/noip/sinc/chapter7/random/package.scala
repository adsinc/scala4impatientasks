package org.noip.sinc.chapter7

/**
 * @author dolgiy
 */
package object random {
	private val a = 1664525
	private val b = 1013904223
	private val n = 32
	private var previous: Double = 0
	private def next: Double = previous * a + b % math.pow(2, n)

	def nextInt(): Int = nextDouble().toInt
	def nextDouble(): Double = { previous = next; previous }
	def setSeed(seed: Int) = previous = seed
}