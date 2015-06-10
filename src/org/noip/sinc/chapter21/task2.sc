implicit class Prc(val a: Int) {
	def +%(b: Int) = a + a * 0.01 * b
}
120 +% 10