package ch11.task

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 13.06.13
 * Time: 15:28
 */
class Table {
	private val html = new ArrayBuffer[String]()
	def |(text: String) = {
		html += "<td>%s</td>\n".format(text)
		this
	}
	def ||(text: String): Table = {
		html += "</tr>\n<tr>\n<td>%s</td>".format(text)
		this
	}

	override def toString: String = "<table>\n<tr>\n%s</tr>\n</table>".format(html.mkString)
}

object Table {
	def apply() = new Table()
}

object TableTest extends App {
	println(Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | ".NET")
}
