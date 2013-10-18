package org.noip.sinc.chapter8.task7

import java.awt.Rectangle

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 16:50
 */
class Square(x: Int, y: Int, width: Int) extends Rectangle(x, y, width, width){
	def this(width: Int) = {
		this(0, 0, width)
	}
	def this() = {
		this(0)
	}
}

object SquareTest extends App {
	val squares = Array(
		new Square()
		, new Square(10)
		, new Square(1, 2, 12)
	)
	for(square <- squares) {
	   println("x=" + square.x + " y=" + square.y + " w=" + square.width + " h=" + square.height)
	}
}
