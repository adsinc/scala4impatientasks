class Money(dol: Int, cnt: Int) {
  val dollar: Int = dol + cnt / 100
  val cent: Int = cnt % 100

  def + (other: Money) = eval(other, _ + _)

  def - (other: Money) = eval(other, _ - _)

  def == (other: Money) =
    dollar == other.dollar && cent == other.cent

  def < (other: Money) =
    (dollar * 100 + cent) < (other.dollar * 100 + other.cent)

  private def eval(other: Money, f: (Int, Int) => Int) =
    new Money(f(dollar,other.dollar), f(cent, other.cent))

  override def toString: String = "$%d.%d".format(dollar, cent)
}
object Money {
  def apply(dollar: Int, cent: Int) = new Money(dollar, cent)
}
val a = Money(1, 75)
val b = Money(0, 50)