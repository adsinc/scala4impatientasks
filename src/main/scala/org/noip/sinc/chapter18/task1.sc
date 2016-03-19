class Bug {
	var x = 0
	var d = 1
	def move(i: Int): this.type = {x += i * d; this}
	def turn(): this.type = {d *= -1; this}
	def show(): this.type = {println(x); this}
}
val bugsy = new Bug
bugsy.move(4).show().move(6).show().turn().move(5).show()