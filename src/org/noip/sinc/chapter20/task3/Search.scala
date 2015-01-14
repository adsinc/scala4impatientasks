package org.noip.sinc.chapter20.task3

import java.io.File

import scala.actors.Actor
import scala.io.Source
import scala.util.matching.Regex

object Search extends App {
  Visitor("""[.]*App[.]*""".r).start() ! BaseDirMsg(new File("src"))
}

case class Visitor(regex: Regex) extends BaseActor {

  var stopActor = false

  val acc: Accumulator = {
    val a = new Accumulator()
    a.start()
    a
  }

  val onReceive: PartialFunction[Any, Unit] = {
    case BaseDirMsg(dir) => visitFiles(dir); stopActor = true
    case _ => new Error("Incorrect msg")
  }

  def visitFiles(f: File): Unit =
    if(f.isDirectory) f.listFiles() foreach visitFiles
    else Matcher(regex, acc).start() ! BaseDirMsg(f)
}

case class Matcher(r: Regex, acc: Accumulator) extends BaseActor {

  var stopActor: Boolean = false

  val onReceive: PartialFunction[Any, Unit] = {
    case BaseDirMsg(file) => {
      r.findFirstIn(Source.fromFile(file).mkString) match {
        case Some(s) => acc ! file
        case None =>
      }
      stopActor = true
    }
    case _ => new Error("Incorrect msg")
  }
}

class Accumulator extends BaseActor {

  def stopActor: Boolean = false

  val onReceive: PartialFunction[Any, Unit] = {
    case f: File => println(f.getAbsolutePath)
  }
}

case class BaseDirMsg(dir: File)

trait BaseActor extends Actor {
  def stopActor: Boolean

  val onReceive: PartialFunction[Any, Unit]

  def act() {
    while(!stopActor) {
      receive (onReceive)
    }
  }
}
