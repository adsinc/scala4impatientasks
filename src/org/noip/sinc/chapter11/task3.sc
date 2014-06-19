class Fraction(n: Int, d: Int) {
  private val num: Int = if(d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if(d == 0) 0 else d * sign(d) / gcd(n, d)

  def sign(a: Int) =
    if(a > 0) 1
    else if(a < 0) -1
    else 0

  def gcd(a: Int, b: Int): Int =
    if(b == 0) math.abs(a)
    else gcd(b, a % b)

  def +(that: Fraction) = new Fraction(
    num * that.den + that.num * den,
    den * that.den
  )

  def -(that: Fraction) = new Fraction(
    num * that.den - that.num * den,
    den * that.den
  )

  def *(that: Fraction) = new Fraction(
    num * that.num,
    den * that.den
  )

  def /(that: Fraction) = new Fraction(
    num * that.den,
    den * that.num
  )

  override def toString = num + "/" + den
}
val a = new Fraction(2, 4)
val b = new Fraction(1, 4)
val c = new Fraction(1, 6)
val d = new Fraction(2, 1)
a + b
a - c
a * d
a / d