class Employee(val name: String, var salary: Double) {
	def this() { this("John Snow", 100) }
}
class Employee1 {
	private var nameVar = "John Snow"
	def name = nameVar
	var salary = 100.0

	def this(name: String, salary: Double) {
		this()
		nameVar = name
		this.salary = salary
	}
}

val e = new Employee1()
e.name
e.salary

val e1 = new Employee1("Robert", 10000)
e1.name
e1.salary
