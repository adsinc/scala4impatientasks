val lst = List[Any](List(3, 8), 2, List(5))

def leafSum(lst: List[Any]): Int = lst match {
  case Nil => 0
  case (h: Int) :: t => h + leafSum(t)
  case (h: List[Any]) :: t => leafSum(h) + leafSum(t)
}

leafSum(lst)