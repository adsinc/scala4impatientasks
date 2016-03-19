class Time(hrs: Int, mins: Int) {
	private val data: Int = (hrs * 60 + mins) % 1440
	def minutes: Int = data % 60
	def hours: Int = data / 60
	def before(other: Time) = this.data < other.data
}
val t1 = new Time(0, 10)
val t2 = new Time(0, 5)
val t3 = new Time(2, 1)
val t4 = new Time(3, 20)
t2 before t3
t1 before t2
t2 before t1
t2 before t3
t3 before t4
