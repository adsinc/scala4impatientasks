val lst = List(1, 2, 3, 4, 5, 6)
(lst :\ List[Int]())((n, acc) => acc :+ n)