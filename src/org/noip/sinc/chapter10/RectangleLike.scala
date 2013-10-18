package org.noip.sinc.chapter10

import java.awt.geom.Ellipse2D
import java.awt.Rectangle

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 10.06.13
 * Time: 12:40
 */
trait RectangleLike {
	def getX(): Double
	def getY(): Double
	def getWidth(): Double
	def getHeight(): Double
	def setFrame(x: Double, y: Double, w: Double, h: Double)

	def translate(dx: Int, dy: Int) { setFrame(getX() + dx, getY() + dy, getWidth(), getHeight()) }
	def grow(h: Int, v: Int) { setFrame(getX(), getY(), getWidth() + 2 * v, getHeight() + 2 * h) }
}

object RectangleLikeTest extends App {
	val egg = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
	println(egg.x + " " + egg.y + " " + egg.width + " " + egg.height)
	egg.translate(10, -10)
	println(egg.x + " " + egg.y + " " + egg.width + " " + egg.height)
	egg.grow(10, 20)
	println(egg.x + " " + egg.y + " " + egg.width + " " + egg.height)
}