import scala.xml.Elem

def f(m: Map[String, String]) =
	<dl>{for { (k, v) <- m } yield <dt>{k}</dt><dd>{v}</dd>}</dl>

val m = Map("A" -> "1", "B" -> "2")
val xml = f(m)
def g(r: Elem): Map[String, String] = {
	def extrVals(tag: String) = r \ tag map (_.text)
	(extrVals("dt") zip extrVals("dd")).toMap
}

g(f(m)) == m
