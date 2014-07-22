def transform(a: Array[Double], col: Int) = (a grouped col).toArray
val a = Array[Double](1, 2, 3, 4, 5, 6)

transform(a, 3) map (_ mkString " ") mkString "\n"