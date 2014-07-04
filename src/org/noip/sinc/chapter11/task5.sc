class Table(val s: String) {
  private def this() = {
    this("")
  }
  def | (s: String) = ???
  def || (s: String) = ???
  override def toString: String = "<table>" + s + "</table>"
}

object Table {
  def apply() = new Table
}
val s = Table() | "1"
println(s)