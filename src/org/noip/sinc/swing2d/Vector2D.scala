package org.noip.sinc.swing2d

/**
 * Represents 2 dimensional vector values
 */
case class Vector2D(x: Double, y: Double) {
  def unary_- = this * -1

  def *(s: Double) = Vector2D(x * s, y * s)

  def +(other: Vector2D) = Vector2D(x + other.x, y + other.y)

  override def toString = s"$x $y"
}

object Vector2D {
  val Zero = Vector2D(0, 0)
}