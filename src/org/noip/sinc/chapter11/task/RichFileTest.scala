package ch11.task

object RichFile {
//	def unapply(input: String) = {
//		val pos = input.lastIndexOf("/")
//		if(pos == -1) None else Some(input.take(pos), input.drop(pos + 1).takeWhile(_ != '.'), input.drop(pos).dropWhile(_ != '.'))
//	}
	def unapplySeq(input: String): Option[Seq[String]] = {
		if(input.trim == "") None else Some(input.trim.split("/"))
	}
}
/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 20.06.13
 * Time: 11:58
 */
object RichFileTest extends App {
	val RichFile(path, name, ext) = "home/sinc/name.torrent"
	println("path=%s\nname=%s\next=%s".format(path, name, ext))
}
