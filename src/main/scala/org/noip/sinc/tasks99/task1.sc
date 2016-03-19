def last[T](ls: List[T]): T = ls match {
  case Nil => sys.error("Source list is empty")
  case h :: Nil => h
  case _ :: t => last(t)
}
last(List(1, 1, 2, 3, 5, 8))
last((1 to 10000).toList)