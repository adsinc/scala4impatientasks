class Car(val supplier: String, val model: String, val year: Int, var number: String) {
	def this(supplier: String, model: String, year: Int) {
		this(supplier, model, year, "")
	}
	def this(supplier: String, model: String, number: String) {
		this(supplier, model, -1, number)
	}
	def this(supplier: String, model: String) {
		this(supplier, model, -1)
	}
}