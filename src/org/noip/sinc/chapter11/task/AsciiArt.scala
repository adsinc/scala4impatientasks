package ch11.task

import scala.io.Source
import math.max

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 14.06.13
 * Time: 11:38
 */
class AsciiArt(private val data: Array[String]) {
	override def toString: String = data.mkString("\n")

	/**
	 * Объединение по горизонтали.
	 * @param that
	 * @return
	 */
	def &(that: AsciiArt) = {
		val maxLen = max(data.length, that.data.length)
		val left = normalize(data, maxLen)
		val right = normalize(that.data, maxLen)
		val union = (for(i <- 0 until maxLen) yield left(i) + right(i)).toArray
		AsciiArt(union)
	}

	/**
	 * Объединение по вертикали.
	 * @param that
	 */
	def |(that: AsciiArt) = AsciiArt(data ++ that.data)

	/**
	 * Выравнивает размеры рисунков.
	 * @param array
	 * @param dim
	 * @return
	 */
	def normalize(array: Array[String], dim: Int) = {
		val arr = (if(array.length == dim) array else array ++ new Array[String](dim - array.length)).toArray
		val buf = arr.map((x) => {if(x == null) "" else x}).toArray
		val maxStrLen = buf.maxBy(_.length).length
		for(b <- buf; delta = maxStrLen - b.length) yield b + (" " * delta)
	}

}

object AsciiArt {
	def apply(data: Array[String]) = new AsciiArt(data)
}

object AsciiArtTest extends App {
	val bear = AsciiArt(Source.fromFile("bear.txt").getLines().toArray)
	val hello = AsciiArt(Source.fromFile("hello.txt").getLines().toArray)
//	println(bear & hello)
	println(bear & hello | bear & hello)
//	println(bear | hello)
//	println(hello | bear)
}