def drop[T](n: Int, ls: List[T]): List[T] = if(ls.isEmpty) Nil else {
  val (l, r) = ls splitAt n
  (l take n - 1) ::: drop(n, r)
}
drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))