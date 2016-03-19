import java.io.File

import scala.xml.XML

val f = new File("/home/dolgiy/scalaProgs/scala4impatientasks/doc.xhtml")
val root = XML.loadFile(f)

val ae = root \\ "img" filter (_.attribute("alt") == None)

ae foreach println