class Union(n: Int) {
  private val data = (0 until n).toBuffer
  private val sz = data map (x => 1)

  def join(p: Int, q: Int): Union = {
    val i = root(p)
    val j = root(q)
    if(i != j) {
      if (sz(i) < sz(j)) {
        data(i) = j
        sz(j) = sz(j) + sz(i)
      } else {
        data(j) = i
        sz(i) = sz(i) + sz(j)
      }
    }
    this
  }


  def connected(a: Int, b: Int): Boolean = root(a) == root(b)

  def root(a: Int): Int = if(data(a) == a) a else root(data(a))
  override def toString: String = {
    data mkString " "
  }
}
val u = new Union(10)
val u1 = u join(4, 3)
val u2 = u1 join(3, 8)
val u3 = u2 join(6, 2)
val u4 = u3 join(0, 8)
val u5 = u4 join(7, 9)
val u6 = u5 join(3, 5)
val u7 = u6 join(2, 9)
val u8 = u7 join(6, 8)
val u9 = u8 join(6, 1)
u9 connected(1, 2)
