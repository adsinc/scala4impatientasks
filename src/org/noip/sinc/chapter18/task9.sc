abstract class Dim[T](val value: Double, val name: String) {
	this: T =>
	protected def create(v: Double): T
	def +(other: Dim[T]) = create(value + other.value)
	override def toString = s"$value $name"
}
class Seconds(v: Double) extends Dim[Seconds](v, "s") {
	override def create(v: Double) = new Seconds(v)
}
class Meters(v: Double) extends Dim[Meters](v, "m") {
	override def create(v: Double) = new Meters(v)
}
val s1 = new Seconds(10)
val s2 = s1.create(20)
val m1 = new Meters(30)
val m2 = m1.create(40)
s1 + s2
m1 + m2