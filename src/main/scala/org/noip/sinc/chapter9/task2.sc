import java.io.PrintWriter
import scala.io.Source
val path1 = "/home/dolgiy/scalaProgs/scala4impatientasks/tabs"
val path2 = "/home/dolgiy/scalaProgs/scala4impatientasks/tabs_out"
val lines = Source.fromFile(path1, "UTF-8").getLines()
def tabsToWhiteSpace(s: String, tabSize: Int) = {
  def loop(acc: String, idx: Int): String = {
    def processChar(c: Char) =
      if (c == '\t') " " * (tabSize - (acc.length % tabSize))
      else c.toString

    if(idx == s.length) acc
    else loop(acc + processChar(s(idx)), idx + 1)
  }
  loop("", 0)
}
val out = new PrintWriter(path2)
out.write(lines.map(tabsToWhiteSpace(_, 8)).mkString("\n"))
out.close()