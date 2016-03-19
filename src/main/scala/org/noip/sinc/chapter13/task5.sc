def makeString(seq: Iterable[Any], delim: String) =
  seq reduceLeft (_ + delim + _)

val l = List(1, 2, 3, 4)

makeString(l, " | ")
