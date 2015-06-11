def length[T](ls: List[T]): Int = {
  def loop(ls: List[T], n: Int): Int =
    if(ls.isEmpty) n
    else loop(ls.tail, n + 1)
  loop(ls, 0)
}
length(List(1, 1, 2, 3, 5, 8))