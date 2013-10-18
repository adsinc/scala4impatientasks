//9
import java.io.PrintWriter
import scala.xml.parsing.XhtmlParser
import scala.xml.transform.{RuleTransformer, RewriteRule}
import scala.xml._
def convert(path: String) {
  val parser = new XhtmlParser(io.Source.fromFile(path))
  val document = parser.initialize.document()
  val root = document.docElem
  val rule1 = new RewriteRule {
    override def transform(n: Node): Seq[Node] = n match {
      case img @ <img>{_*}</img> if(img.attributes("alt").text == "") => img.asInstanceOf[Elem] % Attribute(null, "alt", "TODO", Null)
      case _ => n

    }
  }
  val transformed = new RuleTransformer(rule1).transform(root)
  val printer = new PrettyPrinter(width = 100, step = 4)
  val str = printer.formatNodes(transformed)
  val out = new PrintWriter("transformed.xhtml")
  out.print(str)
  out.close()
}
convert("cj.xhtml")

//8
//import scala.xml.Elem
//def xmlToMap(el: Elem) = ((el \ "dt").map(_.text) zip ((el \ "dd").map(_.text))).toMap
//7
//def mapToXml(map: Map[String, String]) = <dl>{for((k, v) <- map) yield <dt>{k}</dt><dd>{v}</dd>}</dl>
//val m = Map("A" -> "1", "B" -> "2")
//mapToXml(m)
//xmlToMap(mapToXml(m))
//4
//val parser = (new org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl).newSAXParser
//val html = XML.withSAXParser(parser).load("doc.xhtml")
//val images = (html \\ "img").filterNot(_.attributes("alt").isDefined)
//3
//<li>Fred</li> match { case <li>{Text(t)}</li> => t }
//<li>{Text("Fred")}</li> match { case <li>{Text(t)}</li> => t }
//2
//<ul>
//	<li>Opening bracket: [</li>
//	<li>Closing bracket: ]</li>
//	<li>Opening brace: {{</li>
//	<li>Closing brace: }}</li>
//</ul>
//1
//<fred/>(0)
//<fred/>(0)(0)
