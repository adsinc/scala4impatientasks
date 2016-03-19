package org.noip.sinc.chapter20.task1

import scala.actors._

import scala.util.Random.nextDouble
import Helper._

object Main extends App {
	val cnt = 10000000
	val ns = generateNumbers(cnt)

	stopWatch() {
		avg(ns)
	}

	stopWatch() {
		avgActor(ns, 0)
	}
	stopWatch() {
		avgActor(ns, 1)
	}
	stopWatch() {
		avgActor(ns, 2)
	}
	stopWatch() {
		avgActor(ns, 3)
	}
	stopWatch() {
		avgActor(ns, 4)
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

	def avgActor(d: Seq[Double], pow: Int = 4) = {
		val batchCount = math.pow(2, pow).toInt
		val acc = new Accumulator(batchCount, d.size)
		acc.start()
		val pool = 1 to batchCount map (_ => new Summer(acc))
		pool foreach (_.start())
		val parts = split(d, pow)
		pool zip parts foreach (p => p._1 ! DataToSum(p._2))
		pool foreach (_ ! Quit())
	}

	def split(d: Seq[Double], pow: Int): List[Seq[Double]] = pow match {
		case 0 => d :: Nil
		case n => {
			val (l, r) = d splitAt(d.size / 2)
			split(l, pow - 1) ::: split(r, pow - 1)
		}
	}
}

trait Msg
case class Result(result: Double) extends Msg
case class DataToSum(data: Seq[Double]) extends Msg
case class Quit(msg: String = "Job done") extends Msg

class Accumulator(var batchCount: Int, size: Int) extends Actor {
	override def act {
		var totalSum = 0.0
		var received = 0
		while (received < batchCount) {
			receive {
				case Result(sum) => totalSum += sum; println(sum); received += 1
			}
		}
		println(totalSum / size)
	}
}

class Summer(acc: Accumulator) extends Actor {
	override def act {
		var exit = false
		while (!exit) {
			receive {
				case DataToSum(data: Seq[Double]) => acc ! Result(data.sum)
				case Quit(msg) => println(msg); exit = true
			}
		}
	}
}