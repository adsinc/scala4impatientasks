package org.noip.sinc.chapter20.task3

import scala.actors.Actor

object Search extends App {

}

class Visitor extends BaseActor {

  override def stopActor: Boolean = ???

  override val onReceive: PartialFunction[Any, Unit] = _
}

class Matcher extends BaseActor {

  override def stopActor: Boolean = ???

  override val onReceive: PartialFunction[Any, Unit] = _
}

class Accumulator extends BaseActor {

  override def stopActor: Boolean = ???

  override val onReceive: PartialFunction[Any, Unit] = _
}

case class Msg(msg: String)

trait BaseActor extends Actor {
  def stopActor: Boolean

  val onReceive: PartialFunction[Any, Unit]

  def act() {
    while(!exit) {
      receive (onReceive)
    }
  }
}
