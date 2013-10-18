/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 11.06.13
 * Time: 16:59
 */
object Name {
	def unapply(input: String) = {
		val pos = input.indexOf(" ")
		if(pos == -1) None
		else Some(input.substring(0, pos), input.substring(pos + 1))
	}
}
val name = "Cay Horstmann"
val Name(first, last) = name


case class Currency(value: Double, unit: String)
Currency(10.5, "USD")

object Number {
	def unapply(input: String): Option[Int] = {
	    try {
			Some(Integer.parseInt(input.trim))
		} catch {
			case ex: NumberFormatException => None
		}
	}
}

val Number(n) = " 334 "

object IsCompound {
	def unapply(input: String) = input.contains(" ")
}

class Name {
	def unapplySeq(input: String): Option[Seq[String]] =
		if(input.trim == "") None else Some(input.trim.split("\\s+"))
}