def flatten[T](ls: List[Any]): List[T] = ls match {
  case Nil => Nil
  case (h: List[Any]) :: t => flatten(h) ::: flatten(t)
  case (h: T) :: t => h :: flatten(t)
}
flatten[Int](List(List(1, 1), 2, List(3, List(5, 8))))

def flatten2[T](ls: List[Any]): List[T] = ls flatMap {
  case l: List[_] => flatten2(l)
  case e: T => List(e)
}
flatten2(List(List(1, 1), 2, List(3, List(5, 8))))