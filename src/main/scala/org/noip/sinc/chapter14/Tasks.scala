package org.noip.sinc.chapter14

object Tasks {
  def sumCalc(xs: List[Option[Int]]): Int =
    (xs map (_ getOrElse 0)).sum

  def compose(fx: Double => Option[Double], fy: Double => Option[Double]): Double => Option[Double] =
    x => fx(x) flatMap fy
}

object Test extends App {
  def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None

  def g(x: Double) = if (x == 1) Some(math.sqrt(x)) else None

  val h = Tasks.compose(f, g)
  println(h(2))
  println(h(1))
  println(h(0))
}