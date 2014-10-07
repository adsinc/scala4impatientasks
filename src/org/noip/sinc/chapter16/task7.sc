def f(m: Map[String, String]) =
	<dl>{for { (k, v) <- m } yield <dt>{k}</dt><dd>{k}</dd>}</dl>

val m = Map("A" -> "1", "B" -> "2")
f(m)

