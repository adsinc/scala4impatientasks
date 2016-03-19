val a = Array(1, 2, 3, 4, 5, 6)
for (i1 <- 0 until(a.length, 2); i2 = if(i1 < a.length - 1) i1 + 1 else i1) {
	println(i1 + " " + i2)
	val t = a(i1)
	a(i1) = a(i2)
	a(i2) = t
}
a.mkString(",")