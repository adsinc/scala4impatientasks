import scala.collection.SortedMap
import scala.io.Source
val words = Source.fromFile("scalaProgs/scala4impatientasks/words", "UTF-8").mkString.split("\\s+").toList
def countWords(words: List[String]): SortedMap[String, Int] = {
	words match {
		case Nil => SortedMap[String, Int]()
		case h :: t => {
			val m = countWords(t)
			m + (h -> (m.getOrElse(h, 0) + 1))
		}
	}
}
val cnt = countWords(words)
println(cnt.mkString("\n"))


































