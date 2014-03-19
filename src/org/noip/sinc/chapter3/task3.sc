val a = Array(1, 2, 3, 4, 5, 6)
val b = for {
	i <- 0 until(a.length, 1)
	k = i + (if(i%2 == 0) 1 else -1)
} yield a(k)
b.mkString(",")