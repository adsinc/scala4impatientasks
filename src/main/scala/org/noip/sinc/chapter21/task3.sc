implicit class Fact(val n: Int) {
	def i = (1 to n) reduceLeft (_ * _)
}
5i
