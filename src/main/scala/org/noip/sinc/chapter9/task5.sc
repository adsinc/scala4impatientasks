import java.io.PrintWriter
import scala.math.pow
val path = "/home/dolgiy/scalaProgs/scala4impatientasks/exps_out"
val out = new PrintWriter(path)
val pows = 0 to 20 map { pow(2, _) }
val maxLen = pows.last.toString.length
pows foreach { p => out.println(p + " " * (maxLen - p.toString.length + 1) + 1/p) }
out.close()
