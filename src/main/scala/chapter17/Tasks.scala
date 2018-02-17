package chapter17

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Tasks {
  def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] =
    t => f(t) flatMap (u => g(u))

  def doInOrderSeq[T](fs: Seq[T => Future[T]]): T => Future[T] =
    fs.reduceLeft(doInOrder[T, T, T])

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] =
    t => f(t) zip g(t)
}

object Test1 extends App {
  val start = System.currentTimeMillis()
  val res = for {
    n1 <- Future {
      Thread.sleep(1000)
      println(s"Thread1: ${Thread.currentThread().getId}")
      1
    }
    n2 <- Future {
      Thread.sleep(1000)
      println(s"Thread2: ${Thread.currentThread().getId}")
      42
    }
  } yield {
    println(s"Sum thread: ${Thread.currentThread().getId}")
    n1 + n2
  }
  Await.ready(res, 10.seconds)
  println(s"$res calculated at ${System.currentTimeMillis() - start}")
}

object Test2 extends App {
  val squareString = Tasks.doInOrder[String, Int, Int](
    s => Future.successful(s.toInt),
    i => Future.successful(i * i)
  )

  println(Await.ready(squareString("10"), 1.second))
}

object Test3 extends App {
  val res = Tasks.doInOrderSeq[Int](Seq(
    x => Future(x - 5),
    x => Future(x * 10),
    x => Future(x + 100)
  ))

  println(Await.ready(res(1), 1.second))
}

object Test4 extends App {
  val res = Tasks.doTogether[String, String, String](
    x => Future(x.toUpperCase()),
    x => Future(x.trim),
  )

  println(Await.ready(res("   Hello  "), 1.second))
}
