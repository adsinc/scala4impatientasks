import java.io.File

import scala.xml._
import scala.xml.transform.{RuleTransformer, RewriteRule}

val f = new File("/home/dolgiy/scalaProgs/scala4impatientasks/doc.xhtml")
val root = XML.loadFile(f)
val rule = new RewriteRule {
	override def transform(n: Node) = n match {
		case e @ <img>{_*}</img> if e.attribute("alt") == None =>
			e.asInstanceOf[Elem] % Attribute(null, "alt", "TODO", Null)
		case _ => n
	}
}
println(new RuleTransformer(rule).transform(root))
println(root)