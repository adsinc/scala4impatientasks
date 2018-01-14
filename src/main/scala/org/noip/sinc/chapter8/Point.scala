package org.noip.sinc.chapter8

class Point private(val data: Long) extends AnyVal {
  def x: Int = (data >> 32).toInt
  def y: Int = data.toInt
  override def toString: String = s"x=$x y=$y"
}

object Point {
  def apply(x: Int, y: Int): Point = new Point((x.toLong << 32) + y)
}

