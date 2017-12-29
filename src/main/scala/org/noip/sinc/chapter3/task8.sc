import scala.collection.mutable.ArrayBuffer

val a = ArrayBuffer(5, 2, -3, -4, -3, 4)

(for(i <- a.indices if a(i) < 0) yield i)
  .drop(1)
  .reverse
  .foreach(a.remove)

a