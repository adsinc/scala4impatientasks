class ASCIIArt(paramData: Array[String]) {
  private val data = justify(paramData)

  def + (other: ASCIIArt) = new ASCIIArt(data ++ other.data)

  def * (other: ASCIIArt) = {
    val maxRows = math.max(data.length, other.data.length)
    val left = justify(this resizeTo maxRows)
    val rigth = justify(other resizeTo maxRows)
    val newData = (
      for {
        i <- 0 until maxRows
      } yield left(i) + rigth(i)).toArray
    new ASCIIArt(newData)
  }

  private def resizeTo(newSize: Int) =
    data ++ {0 until (newSize - data.length) map {s => ""}}

  private def justify(a: Array[String]) = {
    val width = (a maxBy {_.length}).length
    a map {s => s + " " * (width - s.length)}
  }

  override def toString = data mkString "\n"
}

val art1 = new ASCIIArt("""
(\___/)
(='.'=)
(")_(")
""".split("\n").toArray)

val art2 = new ASCIIArt("""
(\___/)
(='.'=)
(")_(")
""".split("\n").toArray)

val art3 = new ASCIIArt("""
    .-.
   ( . )
 .-.':'.-.
(  =,!,=  )
 '-' | '-'
""".split("\n").toArray)

art1 * art3 * art1 + art1 * art3 * art3
