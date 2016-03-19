import scala.collection.mutable.ArrayBuffer

val a = Array(2, 0, 3, -1, 1, 5, 9, -5)
a.sortWith(_ > _) copyToArray a
a.mkString(",")

val b = ArrayBuffer(2, 0, 3, -1, 1, 5, 9, -5)
b ++= b.sortWith(_ > _)
b.trimStart(b.length/2)
b.mkString(",")