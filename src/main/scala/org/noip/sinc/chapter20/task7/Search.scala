package org.noip.sinc.chapter20.task7

import java.io.{IOException, File}

import scala.actors.{Exit, Actor}
import scala.io.Source
import scala.util.matching.Regex

object Search extends App {
  Visitor("""class""".r).start() ! BaseDirMsg(new File("src"))
}

case class Visitor(regex: Regex) extends BaseActor {

  var stopActor = false

  lazy val reg = {
    val r = Registrator(acc)
    r.start()
    r
  }

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
    else {
      Matcher(regex, acc, reg).start() ! BaseDirMsg(f)
      1
    }
}

case class Matcher(r: Regex, acc: Accumulator, reg: Registrator) extends BaseActor {

  var stopActor: Boolean = false


  override def act(): Unit = {link(reg); super.act()}

  val onReceive: PartialFunction[Any, Unit] = {
    case BaseDirMsg(file) => {
      if(file.getAbsolutePath.contains("Search"))
        throw new IOException
      acc ! Result(r.findAllMatchIn(Source.fromFile(file).mkString).size)
      stopActor = true
    }
    case _ => new Error("Incorrect msg")
  }
}

class Accumulator extends BaseActor {

  var fcnt = 0
  var received = 0
  var result = 0
  var inited = false

  def stopActor = inited && received >= fcnt

  val onReceive: PartialFunction[Any, Unit] = {
    case Result(cnt) => result += cnt; received += 1
    case cnt: Int => inited = true; fcnt += cnt
  }

  override def beforeExit() = println(result)
}

case class Registrator(acc: Accumulator) extends Actor {
  def act() {
    trapExit = true
    while(true) {
      receive {
        case Exit(linked, e: IOException) => println(e.getMessage); acc ! -1
      }
    }
  }
}

case class BaseDirMsg(dir: File)
case class Result(count: Int)

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
