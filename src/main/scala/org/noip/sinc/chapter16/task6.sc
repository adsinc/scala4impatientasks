import java.io.File

import scala.xml.{Text, XML}

val f = new File("/home/dolgiy/scalaProgs/scala4impatientasks/doc.xhtml")
val root = XML.loadFile(f)

val refs = root \\ "a" map {
	case n @ <a>{Text(name)}</a> => name -> n.attribute("href").get
}

val l = (refs map (_._1.length)).max

refs foreach (p => println(s"${p._1} ${" " * (l - p._1.length)} ${p._2}"))