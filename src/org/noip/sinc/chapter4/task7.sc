import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.collection.mutable.Map

val props: Map[String, String] = System.getProperties
val commonLen = {props map { case (k, v) => k.length + v.length }}.max
val delim = '\n' + "-" * (commonLen + 1)
val maxKeyLen = props.keys.maxBy(_.length).length
println(delim)
props foreach { case (k, v) => println(k + " " * (maxKeyLen - k.length) + "|" + v) }

println(delim)

