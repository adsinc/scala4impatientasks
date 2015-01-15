package org.noip.sinc.chapter20.task4

import java.io.File

import scala.actors.Actor
import scala.collection.immutable.TreeSet
import scala.io.Source
import scala.util.matching.Regex

object Search extends App {
  Visitor("c[\\w]*s".r).start() ! BaseDirMsg(new File("src"))
}

case class Visitor(regex: Regex) extends BaseActor {

  var stopActor = false

  val acc: Accumulator = {
    val a = new Accumulator()
    a.start()
    a
  }

  val onReceive: PartialFunction[Any, Unit] = {
    case BaseDirMsg(dir) => acc ! visitFiles(dir);stopActor = true
    case _ => new Error("Incorrect msg")
  }

  def visitFiles(f: File): Int =
    if(f.isDirectory) (f.listFiles() map visitFiles).sum
    else {Matcher(regex, acc).start() ! BaseDirMsg(f); 1}
}

case class Matcher(r: Regex, acc: Accumulator) extends BaseActor {

  var stopActor: Boolean = false

  val onReceive: PartialFunction[Any, Unit] = {
    case BaseDirMsg(file) => {
      acc ! Result(r.findAllMatchIn(Source.fromFile(file).mkString).map(_.matched).toSet)
      stopActor = true
    }
    case _ => new Error("Incorrect msg")
  }
}

class Accumulator extends BaseActor {

  var fcnt = -1
  var received = 0
  var result = TreeSet[String]()

  def stopActor = fcnt >= 0 && received >= fcnt

  val onReceive: PartialFunction[Any, Unit] = {
    case Result(r) => result ++= r; received += 1
    case cnt: Int => fcnt = cnt
  }

  override def beforeExit() = result foreach println
}

case class BaseDirMsg(dir: File)
case class Result(matches: Set[String])

trait BaseActor extends Actor {
  def stopActor: Boolean

  def onReceive: PartialFunction[Any, Unit]

  def beforeExit() = ()

  def act() {
    while(!stopActor) {
      receive (onReceive)
    }
    beforeExit()
  }
}
