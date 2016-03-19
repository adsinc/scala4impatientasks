def nth[T](n: Int, ls: List[T]): T = ls match {
  case Nil => throw new NoSuchElementException
  case h :: t if n == 0 => h
  case _ :: t => nth(n - 1, t)
}
nth(2, List(1, 1, 2, 3, 5, 8))