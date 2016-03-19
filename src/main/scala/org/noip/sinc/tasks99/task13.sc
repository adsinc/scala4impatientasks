def encodeDirect[T](ls: List[T]): List[(Int, T)] = ls match {
  case Nil => Nil
  case h :: t =>
    val (l, r) = ls span (_ == h)
    val p: (Int, T) = l.length -> h
    p :: encodeDirect(r)
}
encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))