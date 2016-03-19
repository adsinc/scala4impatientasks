val a = Array(2, 0, 3, -1, 1, 5, 9, -5)
val p = for(i <- 0 until a.length if a(i) > 0) yield a(i)
val n = for(i <- 0 until a.length if a(i) <= 0) yield a(i)
val b = (p ++ n).toArray
b mkString ","