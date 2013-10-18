//import scala.xml._
////16,9
//val list = <ul><li>Fred</li><li>Wilma</li></ul>
//val list2 = list.copy(label = "ol")
//list.copy(child = list.child ++ <li>Another item</li>)
//
//
//
//val image = <img src="hamster.jpg"/>
//val image2 = image % Attribute(null, "alt", "An image of a hamster", Null)
//val image3 = image % Attribute(null, "alt", "An image of a frog",
//	Attribute(null, "src", "frog.jpg`", Null))
//16.7
//val list = <dl><dt>Java</dt><dd>Gosling</dd><dt>Scala</dt><dd>Odersky</dd></dl>
//val languages = list \ "dt"
//16.5
//def makeURL(fileName: String) = "file://" + fileName
//<img src={makeURL("hello.txt")}/>
//<a id={new Atom(1)}/>
//val description = "TODO1"
//val t = <img alt={if(description == "TODO") null else description}/>
//t
//val t1 = <img alt={if(description == "TODO") None else Some(Text(description))}/>
//t1
//16.4
//val items = Array(1, 2, 3, 5)
//<ul>{for(i <- items) yield <li>{i}</li>}</ul>
//16.3
//val elem = <a href="http://scala-lang.org">The <em>Scala</em> language</a>
//
//val url = elem.attributes("href")
//elem.attributes("href").text
//elem.attributes.get("href").getOrElse(Text(""))
//
//val image = <img alt="TODO" src="hamster.jpg"/>
//val map = image.attributes.asAttrMap
//16.2
//val elem = <a href="http://scala-lang.org">The <em>Scala</em> language</a>
//
//elem.label
//elem.child
//val items = new NodeBuffer
//items += <li>Fred</li>
//items += <li>Wilma</li>
//val nodes: NodeSeq = items
// 16.1
//val doc = <html><head><title>Fred's Memoirs</title></head><body>...</body></html>
//val items = <li>Fred</li><li>Wilma</li>
