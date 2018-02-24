package org.noip.sinc.chapter21

import org.noip.sinc.chapter21.Tasks._

object Tasks {

  implicit class AddPercent(n: Int) {
    def +%(p: Int): Int = n * (1 + p / 100)
  }

  trait NumberLike[T] {
    def plus(x: T, y: T): T

    def divideBy(x: T, n: Int): T
  }

  object NumberLike {

    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y

      def divideBy(x: Double, n: Int): Double = x / n
    }

    implicit object NumberLikeBigDecimal extends NumberLike[BigDecimal] {
      def plus(x: BigDecimal, y: BigDecimal): BigDecimal = x + y

      def divideBy(x: BigDecimal, n: Int): BigDecimal = x / n
    }

  }

  def average[T](xs: Seq[T])(implicit ev: NumberLike[T]): T = {
    ev.divideBy(xs.reduce(ev.plus), xs.length)
  }
}

object Test2 extends App {

  import Tasks.AddPercent

  println(120 +% 10)
}

object Test11 extends App {
  println(average(1.to(20, 3).map(_.toDouble)))
  println(average(1.to(20, 3).map(BigDecimal.apply)))
}

