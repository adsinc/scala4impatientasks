class Pair[T, S](val _1: T, val _2: S) {
  def swap = new Pair(_2, _1)
  def swap[K, P](p: Pair[K, P]) = new Pair[P, K](p._2, p._1)
  override def toString = s"${_1} ${_2}"
}
val p1 = new Pair(1, "Hello")
p1.swap(new Pair("hello", 2))