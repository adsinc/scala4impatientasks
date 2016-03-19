import scala.io.Source
val cnt = collection.mutable.Map[String, Int]()
Source.fromFile("scalaProgs/scala4impatientasks/words", "UTF-8")
	.mkString.split("\\s+")
	.foreach( w => cnt += w -> (1 + cnt.getOrElse(w, 0)))
println(cnt.mkString("\n"))



