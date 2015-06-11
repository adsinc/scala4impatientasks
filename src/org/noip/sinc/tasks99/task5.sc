def reverse[T](ls: List[T]): List[T] = {
  def loop(ls: List[T], acc: List[T]): List[T] = ls match {
    case Nil => acc
    case h :: t => loop(t, h :: acc)
  }
  loop(ls, Nil)
}
reverse(List(1, 1, 2, 3, 5, 8))