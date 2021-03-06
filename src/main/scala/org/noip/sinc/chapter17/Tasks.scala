package org.noip.sinc.chapter17

import java.util.concurrent.Executors

import org.noip.sinc.chapter13.Tasks.time

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.io.{Source, StdIn}
import scala.util.{Failure, Success}

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

object Test8 extends App {

  def requestUrl = Future {
    println("Enter url")
    StdIn.readLine()
  }

  def readUrl(url: String) = Future {
    Source.fromURL(url).mkString
  }

  def findAllHRefs(html: String) = Future {
    val href = """href="[^"]*"""".r
    href.findAllIn(html).foreach(println)
  }

  val result = requestUrl flatMap readUrl flatMap findAllHRefs recover {
    case e => println(s"Error: ${e.getMessage}")
  }

  Await.ready(result, 1000.second)
}

object Test11 extends App {
  def printTime(): Unit = {
    Thread.sleep(3000)
    println(System.currentTimeMillis())
  }

  val pool = Executors.newCachedThreadPool()
  implicit val ex: ExecutionContextExecutor = ExecutionContext.fromExecutor(pool)

  val fs = (1 to 80).map(_ => Future(printTime()))
  Await.ready(Future.sequence(fs), 1000.second)

  pool.shutdown()
}

object Test12 extends App {

  def findRefLengths(url: String): Seq[Future[Int]] = {
    val pattern = """href="(https?://[^"]*)"""".r
    val refs = pattern.findAllMatchIn(Source.fromURL(url).mkString)
      .map(m => m.group(1))
      .toSeq
    val ps = refs.map(_ -> Promise[Int]()).toMap
    ps.foreach { case (ref, pr) =>
      Future(Source.fromURL(ref).size) onComplete {
        case Success(l) => pr.success(l)
        case Failure(_) => pr.success(0)
      }
    }
    (ps.values map (_.future)).toSeq
  }

  val f = Future.sequence(findRefLengths("https://eax.me/page/34/"))
  Await.ready(f, 100.second)
  println(f)
}

object Test13 extends App {

  val from = 100000
  val to = Int.MaxValue
  val step = (to - from) / 8

  def isPalindrome(s: String) = s == s.reverse

  val p = Promise[BigInt]()

  Range.inclusive(from, to, step)
    .map(x => x until x + step)
    .foreach(xs => Future {
      val it = xs.iterator
      while (!p.isCompleted && it.hasNext) {
        val xi = BigInt(it.next())
        val primePalindrome = xi.isProbablePrime(10000) && isPalindrome(xi.toString())
        if (primePalindrome) {
          p.trySuccess(xi)
        }
      }
    })

  val result = p.future
  Await.ready(result, 1000.second)
  println(result)
}