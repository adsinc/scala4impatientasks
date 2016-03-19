import scala.collection.mutable.ArrayBuffer
val a = ArrayBuffer(1, -12, 1, -5, 2, 4, -1, -5, 1, 25, 2, -3)
(for(i <- 0 until a.length if a(i) < 0) yield i).tail.reverse foreach(a remove _)
a
