package org.noip.sinc.chapter20.task1

import scala.actors.{OutputChannel, Actor}
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

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
				case Packet(array, sendTo) => sendTo ! array.sum
			}
		}
	}
}

class UnionActor extends Actor {
	val buf = new ArrayBuffer[BigInt]
	var partNumbers = Int.MaxValue
	var sendResultTo: OutputChannel[Any] = null
	var count: Int = 0
	def act() {
		while(buf.length < partNumbers) {
			receive {
				case number: BigInt => {buf += number}
				case Counts(numbersCount, parts) => {
					partNumbers = parts
					sendResultTo = sender
					count = numbersCount
				}
			}
		}
		val result = buf.sum.toDouble / count
		sendResultTo ! (buf.sum.toDouble / count)
	}
}

case class Packet(data: Array[BigInt], sendTo: Actor)
case class Counts(count: Int, parts: Int)

object Mean extends App {
	def profile(arr: Array[BigInt], fn: Array[BigInt] => Double) {
		val start = System.currentTimeMillis()
		val res = fn(arr)
		val time = System.currentTimeMillis() - start
		println("time=%d ms value=%f".format(time, res))
	}

	def countMean(arr: Array[BigInt]) =  numbers.sum.toDouble / numbers.length

	val PART_NUM = 20
	val unionActor = new UnionActor
	unionActor.start()
	val threads = (for(_ <- 0 until PART_NUM) yield { new SumActor().start }).toIterator
	def countMeanPar(arr: Array[BigInt]) = {
		numbers.grouped(numbers.length / PART_NUM).foreach( {
			threads.next() ! new Packet(_, unionActor)
		})
		val result = unionActor !? Counts(numbers.length, PART_NUM)
		result.asInstanceOf[Double]
	}

	val numbers = (for(i <- 1 to 100000) yield BigInt.probablePrime(8, Random)).toArray

	profile(numbers, countMean)
	profile(numbers, countMeanPar)


}

