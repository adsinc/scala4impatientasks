def penultimate[T](ls: List[T]): T = ls match {
  case Nil => throw new NoSuchElementException
  case _ :: Nil => throw new NoSuchElementException
  case e :: _ :: Nil => e
  case _ :: t => penultimate(t)
}
penultimate(List(1, 1, 2, 3, 5, 8))