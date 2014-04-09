class Person(fullName: String) {
	val firstName = fullName.takeWhile(_ != ' ')
	val lastName = fullName.dropWhile(_ != ' ').tail
}
val p = new Person("John Smith")
p.firstName
p.lastName