import scala.io.Source

val path1 = "/home/dolgiy/scalaProgs/scala4impatientasks/numbers"

val numbers = Source.fromFile(path1, "UTF-8").mkString.split("\\s+").map(_.toDouble)

numbers.sum
numbers.max
numbers.min