def swap(a: Array[Int]) = a match {
  case Array(x, y, _*) => a(0) = y; a(1) = x; a
  case _ => a
}
swap(Array(1, 2, 3, 4, 5)) mkString " "
swap(Array(1, 2)) mkString " "
swap(Array(1)) mkString " "