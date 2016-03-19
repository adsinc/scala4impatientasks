def decode[T](ls: List[(Int, T)]) =
  ls flatMap (p => 1 to p._1 map (_ => p._2))

decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))