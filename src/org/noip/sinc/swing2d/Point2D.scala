package org.noip.sinc.swing2d

case class Point2D(x: Double, y: Double) {
  def +(v: Vector2D) = Point2D(x + v.x, y + v.y)
}
