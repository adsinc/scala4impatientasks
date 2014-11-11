trait Action
object show extends Action
object then extends Action
object around

class Bug {
	var x = 0
	var d = 1
	def move(i: Int): this.type = {x += i * d; this}
	def turn(a: around.type): this.type = {d *= -1; this}
	def and(act: Action): this.type = act match {
		case _: show.type => println(x); this
		case _: then.type => this
	}
}

val bugsy = new Bug

bugsy move 4 and show and then move 6 and show turn around move 5 and show