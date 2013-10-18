package org.noip.sinc.chapter8.task6

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 16:34
 */
abstract class Shape(var x: Double, var y: Double) {
	def centerPoint: (Double, Double)
}

class Circle(x: Double, y: Double, r: Double) extends Shape(x, y) {
	def centerPoint: (Double, Double) = (1, 2)
}

class Rectangle(x: Double, y: Double, w: Double, h: Double) extends Shape(x, y) {
	def centerPoint: (Double, Double) = (x + w / 2, y - h / 2)
}

object ShapeTest extends App {
	val rectangle = new Rectangle(-2, 3, 2, 2)
	println(rectangle.centerPoint)
}