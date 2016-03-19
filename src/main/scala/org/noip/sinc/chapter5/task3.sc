class Time(hrs: Int, mins: Int) {
	val minutes: Int = mins % 60
	val hours: Int = (hrs + mins / 60) % 24
	def before(other: Time) = this.hours < other.hours || (this.hours == other.hours && this.minutes < other.minutes)
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
