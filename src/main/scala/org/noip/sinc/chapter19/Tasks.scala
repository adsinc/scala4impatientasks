package org.noip.sinc.chapter19

import org.noip.sinc.chapter19.Tasks._

import scala.collection.mutable.ArrayBuffer
import scala.language.reflectiveCalls

object Tasks {

  class Bug(var pos: Int, private var reverse: Boolean = false) {

    def move(i: Int): this.type = {
      pos += (if (reverse) -1 else 1) * i
      this
    }

    def turn(t: around.type): this.type = {
      turn()
      this
    }

    def turn(): Bug = {
      reverse = !reverse
      this
    }

    def show1(): this.type = {
      println(pos)
      this
    }

    def and(s: show.type): this.type = {
      show1()
      this
    }

    def and(t: then.type): this.type = this
  }

  object Bug {
    def apply(): Bug = new Bug(0)
  }

  object show

  object then

  object around

  class Network(name: String) {
    outer =>

    private val members = new ArrayBuffer[Member]

    def join(name: String): Member = {
      val m = new Member(name)
      members += m
      m
    }

    class Member(val name: String) {
      override def equals(obj: scala.Any): Boolean = obj match {
        case that: Member =>
          if (this.eq(that)) true
          else this.name == that.name
        case _ => false
      }
    }

  }

}

object Test1 extends App {
  val bugsy = Bug()
  bugsy.move(4).show1().move(6).show1().turn().move(5).show1()
}

object Test2 extends App {
  val bugsy = Bug()
  bugsy move 4 and show and then move 6 and show turn around move 5 and show
}

object Test4 extends App {
  val betta = new Network("betta")
  val sigma = new Network("sigma")
  val bettaAlex1 = betta join "Alex"
  val bettaAlex2 = betta join "Alex"
  val sigmaAlex = sigma join "Alex"
  assert(bettaAlex1 == bettaAlex2)
  assert(bettaAlex1 != sigmaAlex)
}

object Test7 extends App {
  def withClose[T <: {def close() : Unit}, V](o: T)(f: T => V): V =
    try {
      f(o)
    } finally {
      o.close()
    }

  class Closable(val name: String) {
    def close(): Unit = println("closed")
  }

  withClose(new Closable("Alex"))(x => println(x.name))
}

object Test8 extends App {
  def printValues[T <: {def apply(n : Int) : Int}](o: T, from: Int, to: Int): Unit = {
    var i = from
    while (i <= to) {
      print(o.apply(i) + " ")
      i += 1
    }
    println()
  }

  val f = (x: Int) => x * x
  println(f.apply(10))

  printValues(Array(1, 2, 3, 4, 5, 7, 88, 9), 3, 6)
  printValues(f, 3, 6)
}
