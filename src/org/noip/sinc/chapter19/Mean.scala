package org.noip.sinc.chapter19

import scala.util.Random
import scala.Array
import scala.actors.Actor

/**
 * Created with IntelliJ IDEA.
 * Date: 02.10.13
 * Time: 1:11
 * @author dolgiy
 */

class SumActor extends Actor {
	def act() {
		while(true) {
			receive {
				case arr: Array[BigInt] => println(arr.sum)
			}
		}
	}
}


object Mean extends App {

//	def profile(arr: Array[BigInt], fn: Array[BigInt] => Double) {
//		val start = System.currentTimeMillis()
//		val res = fn(arr)
//		val time = System.currentTimeMillis() - start
//		println("time=%d ms value=%f".format(time, res))
//	}
//
//	def countMean(arr: Array[BigInt]) =  numbers.sum.toDouble / numbers.length
//
//	def countMeanPar(arr: Array[BigInt]) =  {
//
//		1.1
//	}
//
//	val numbers = (for(i <- 1 to 10000) yield BigInt.probablePrime(8, Random)).toArray
//
//	profile(numbers, countMean)
//	profile(numbers, countMeanPar)

	val sumActor = new SumActor
	sumActor.start() ! Array(1, 2, 3)

}
