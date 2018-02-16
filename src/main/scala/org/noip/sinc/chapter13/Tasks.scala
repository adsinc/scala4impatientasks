package org.noip.sinc.chapter13

import org.noip.sinc.chapter13.Tasks.{removeEachSecond, removeEachSecond2, time}

import scala.collection.immutable
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

object Tasks {

  def removeEachSecond[T](xs: ListBuffer[T]): ListBuffer[T] = {
    for {
      i <- xs.indices.reverse
      if i % 2 == 1
    } xs.remove(i)
    xs
  }

  def removeEachSecond2[T](xs: ListBuffer[T]): immutable.IndexedSeq[T] = {
    for {
      i <- xs.indices
      if i % 2 == 0
    } yield xs(i)
  }

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block
    val t1 = System.currentTimeMillis()
    println("Elapsed time: " + (t1 - t0) + "ms")
    result
  }

  def findInMap(xs: Array[String], m: Map[String, Int]): Array[Int] =
    xs.flatMap(m.get)

  def mkString[A](xs: Seq[A], sep: String): String =
    if (xs.isEmpty) ""
    else xs.tail.foldLeft(xs.head.toString)((s, x) => s + sep + x)

  def group[A: ClassTag](xs: Array[A], cols: Int): Array[Array[A]] =
    (xs grouped cols).toArray

}

object Task3 extends App {
  val r = (1 to 100000).toList

  val t1 = r.to[ListBuffer]
  val t2 = r.to[ListBuffer]

  time {
    removeEachSecond(t1)
  }

  time {
    removeEachSecond2(t2)
  }
}

object Task4 extends App {
  println(Tasks.findInMap(Array("A", "B", "C"), Map("A" -> 10, "C" -> 1)) mkString ",")
}

object Task5 extends App {
  println(Tasks.mkString(Array("A", "B", "C"), "->"))
  println(Tasks.mkString(Array("A"), "->"))
  println(Tasks.mkString(Array[String](), "->"))
}

object Task6 extends App {
  val lst = List(1, 2, 3)
  val l1 = (lst :\ List[Int]()) (_ :: _)
  println(l1)
  val l2 = (List[Int]() /: lst) (_ :+ _)
  println(l2)
  val l3 = (lst :\ List[Int]()) ((x, xs) => xs :+ x)
  println(l3)
  val l4 = (List[Int]() /: lst) ((xs, x) => x :: xs)
  println(l4)
}

object Task7 extends App {
  val xs = List(1, 2, 3, 4)
  val ys = List(5, 6, 7, 8)

  def mult(x: Int, y: Int): Int = x * y

  println(xs zip ys map (mult _).tupled)
}

object Task8 extends App {
  val xs = Array(1, 2, 3, 4, 4, 6)
  println(Tasks.group(xs, 3) map (_.mkString(",")) mkString "\n")
}
