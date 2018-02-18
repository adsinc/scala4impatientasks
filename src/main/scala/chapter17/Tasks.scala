package chapter17

import org.noip.sinc.chapter13.Tasks.time

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.io.StdIn

object Tasks {
  def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] =
    t => f(t) flatMap (u => g(u))

  def doInOrderSeq[T](fs: Seq[T => Future[T]]): T => Future[T] =
    fs.reduceLeft(doInOrder[T, T, T])

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] =
    t => f(t) zip g(t)

  def join[T](fs: Seq[Future[T]]): Future[Seq[T]] =
    fs.foldLeft(Future.successful(Seq.empty[T])) { (acc, ft) =>
      for {
        xs <- acc
        t <- ft
      } yield xs :+ t
    }

  def join2[T](fs: Seq[Future[T]]): Future[Seq[T]] =
    fs.foldLeft(Future.successful(Seq.empty[T])) { (acc, ft) =>
      acc flatMap (s => ft map (s :+ _))
    }

  def repeat[T](action: => T, until: T => Boolean): Future[T] =
    Future(action).flatMap { t =>
      if (until(t)) Future.successful(t)
      else repeat(action, until)
    }
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

object Test5 extends App {
  val res = Tasks.join2(Seq(
    Future.successful(1),
    Future.successful(2)
  ))

  println(Await.ready(res, 1.second))

  val res2 = Future.sequence(Seq(
    Future.successful(1),
    Future.successful(2)
  ))
  println(Await.ready(res2, 1.second))

}

object Test6 extends App {
  val password = Tasks.repeat[String](
    {
      println("Enter password")
      StdIn.readLine()
    },
    {
      Thread.sleep(1000)
      _ == "secret"
    })
  println(Await.ready(password, 1000.second))
}

object Test7 extends App {

  for (_ <- 1 to 5) {
    time {
      val n = 3000000

      def countPrime(xs: Seq[BigInt]) =
        xs count (x => x.isProbablePrime(1000000))

      val intervalCounts = (1 to n)
        .grouped(n / 8)
        .map(xs => Future {
          countPrime(xs map BigInt.apply)
        })
      val result = Future.sequence(intervalCounts).map(_.sum)
      Await.ready(result, 1000.second)
    }
  }
}