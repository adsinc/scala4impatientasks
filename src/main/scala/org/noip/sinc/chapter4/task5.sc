import java.util.TreeMap
import scala.collection.JavaConversions.mapAsScalaMap
import scala.io.Source
import scala.collection.mutable.Map
// var 1
val cnt: Map[String, Int] = new TreeMap[String, Int]()
Source.fromFile("scalaProgs/scala4impatientasks/words", "UTF-8")
	.mkString.split("\\s+")
	.foreach( w => cnt += w -> (1 + cnt.getOrElse(w, 0)))
println(cnt.mkString("\n"))

