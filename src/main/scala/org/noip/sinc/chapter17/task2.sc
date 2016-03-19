class Pair[T](var _1: T, var _2: T) {
  def swap = {
    val tmp = _1
    _1 = _2
    _2 = tmp
    this
  }
  override def toString = s"${_1} ${_2}"
}
val p1 = new Pair(1, 2)
p1.swap