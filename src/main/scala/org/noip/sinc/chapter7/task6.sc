import java.util.{HashSet => JavaSet}
import scala.collection.mutable.{Set => ScalaSet}

val js = new JavaSet[String]()
js.add("Hello")
js.add("World")
js.add("!")
js.add("ololo")

val ss = ScalaSet[String]()
val it = js.iterator()
while(it.hasNext) ss += it.next()

println(ss.mkString(" "))
