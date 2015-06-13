def compress[T](ls: List[T]): List[T] = ls match {
  case Nil => Nil
  case e1 :: e2 :: t if e1 == e2 => compress(e1 :: t)
  case h :: t  => h :: compress(t)
}

compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))