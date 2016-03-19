import scala.xml.Text

<li>Fred</li> match {case <li>{Text(t)}</li> => t }
<li>{Text("Fred")}</li> match {case <li>{Text(t)}</li> => t }