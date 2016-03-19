class Person(ageArg: Int) {
	val age = if(ageArg < 0) 0 else ageArg
}
new Person(-10).age
new Person(20).age