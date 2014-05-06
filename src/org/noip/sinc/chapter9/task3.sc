import scala.io.Source

val path1 = "/home/dolgiy/scalaProgs/scala4impatientasks/words"
Source.fromFile(path1, "UTF-8").mkString.split("\\s+").filter(_.length >= 12).foreach(println)










