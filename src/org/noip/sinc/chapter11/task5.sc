{
  class Table(val pref: String, val str: String, suff: String) {

    def this() = this("<table><tr>", "", "</tr></table>")

    def |(s: String) = new Table(pref, str + "<td>" + s + "</td>", suff)

    def ||(s: String) =
      new Table(pref + str + "</tr>" + "<tr>", "", suff) | s

    override def toString: String = pref + str + suff
  }

  object Table {
    def apply() = new Table("<table>", "", "</table>")
  }

  val t = new Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, /NET"
  println(t)
}