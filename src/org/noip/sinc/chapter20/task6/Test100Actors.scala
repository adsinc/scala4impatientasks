package org.noip.sinc.chapter20.task6

import scala.actors.Actor

/**
 * @author dolgiy
 */
object Test100Actors extends App{
	val whileActors = (for(i <- 0 until 100) yield new WhileActor().start()).toList
	whileActors.foreach(_ ! "Hello")
	val loopActors = (for(i <- 0 until 100) yield new LoopActor().start()).toList
	loopActors.foreach(_ ! "Hello")
}

class WhileActor extends Actor {
	def act = while(true) receive {
		case "Hello" => println(Thread.currentThread())
	}
}

class LoopActor extends Actor {
	def act() = loop {
		react {
			case "Hello" => println(Thread.currentThread())
		}
	}
}