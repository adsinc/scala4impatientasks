package org.noip.sinc.chapter15.tasks.t4

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.07.13
 * Time: 16:26
 */
object AllDiffrent {
	def allDifrent[@specialized(Int, Double) T](x: T, y: T) = { x != y }
}
