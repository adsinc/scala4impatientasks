//10

//9
//abstract class Dim[T](val value: Double, val name: String) {
//  this: T =>
//  protected def create(v: Double): T
//  def +(that: Dim[T]) = create(value + that.value)
//  override def toString: String = value + " " + name
//}
//class Seconds(v: Double) extends Dim[Seconds](v, "s") {
//  protected def create(v: Double) = new Seconds(v)
//}
//class Meters(v: Double) extends Dim[Seconds](v, "s") {
//  protected def create(v: Double) = new Seconds(v)
//}
//val s1 = new Seconds(2)
//val s2 = new Seconds(2)
//println(s1 + s2)
//val m = new Meters(5)
//println(s1 + m)


