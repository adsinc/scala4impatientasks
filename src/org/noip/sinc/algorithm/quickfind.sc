class Union(private val data: Array[Int]) {

  def this(n: Int) = this(0 until n toArray)

  def join(p: Int, q: Int): Union = {
    val i = data(p)
    val j = data(q)
    new Union(data.map(x => if(x == i) j else x))
  }

  def connected(a: Int, b: Int): Boolean = data(a) == data(b)

  override def toString: String = data mkString " "
}

val u = new Union(10)
val u1 = u join(1, 3)
val u2 = u1 join(9, 6)
val u3 = u2 join(5, 0)
val u4 = u3 join(9, 0)
val u5 = u4 join(4, 8)
val u6 = u5 join(5, 3)
