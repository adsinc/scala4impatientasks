package org.noip.sinc.chapter20.task3

import java.io.File

import scala.actors.Actor
import scala.util.matching.Regex

object Search extends App {

}

case class Visitor extends BaseActor {

  var stopActor = false

  val onReceive = {
    case BaseDirMsg(dir, r) => visitFiles(dir, r)
    case _ => new Error("Incorrect msg")
  }

  def visitFiles(f: File, r: Regex): Unit =
    if(f.isDirectory) f.listFiles() foreach (visitFiles(_, r))
    else Matcher(r) ! FileMsg(f)
}

case class Matcher(r: Regex) extends BaseActor {

  override def stopActor: Boolean = ???

  override val onReceive: PartialFunction[Any, Unit] = _
}

class Accumulator extends BaseActor {

  override def stopActor: Boolean = ???

  override val onReceive: PartialFunction[Any, Unit] = _
}

abstract case class Msg(msg: String)
case class FileMsg(file: File) extends Msg("FileMsg")
case class BaseDirMsg(dir: File, regex: Regex) extends FileMsg(dir)

trait BaseActor extends Actor {
  def stopActor: Boolean

  val onReceive: PartialFunction[Any, Unit]

  def act() {
    while(!stopActor) {
      receive (onReceive)
    }
  }
}
