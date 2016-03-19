class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}
class Ant extends Creature {
  override val range = 2
}

val a = new Ant
a.range
a.env.length