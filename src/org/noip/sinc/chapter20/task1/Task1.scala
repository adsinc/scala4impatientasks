package org.noip.sinc.chapter20.task1

import scala.util.Random.nextDouble
import Helper._

object Main extends App {
	val cnt = 10000000
	val ns = generateNumbers(cnt)

	stopWatch() {
		avg(ns)
	}
}


object Helper {
	def stopWatch()(f: => Unit) = {
		val start = System.currentTimeMillis()
		f
		val time = System.currentTimeMillis() - start
		println(s"Executed in $time ms")
	}

	def generateNumbers(n: Int) = 1 to n map (_ => nextDouble())

	def avg(d: Seq[Double]) = d.sum / d.length
}