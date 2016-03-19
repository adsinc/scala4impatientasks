import java.io.PrintWriter
import scala.io.Source
val path = "/home/dolgiy/scalaProgs/scala4impatientasks/words"
val reverseLines = Source.fromFile(path, "UTF-8").getLines().toList.reverse
val out = new PrintWriter(path)
out.write(reverseLines.mkString("\n"))
out.close()