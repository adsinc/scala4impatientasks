def duplicate[T](ls: List[T]): List[T] =
  ls flatMap (e => List(e, e))
duplicate(List('a, 'b, 'c, 'c, 'd))