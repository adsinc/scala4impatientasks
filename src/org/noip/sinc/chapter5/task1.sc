class Counter {
	private var value = 0
	def increment() = if(value == Int.MaxValue) value else value += 1
	def current() = value
}

val counter = new Counter
for(i <- 0 to Int.MaxValue - 1) counter.increment()
counter.increment()
println(counter.current())


