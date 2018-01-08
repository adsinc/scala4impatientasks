def lteqgt(values: Array[Int], n: Int): (Int, Int, Int) =
  (values.count(_ < n), values.count(_ == n), values.count(_ > n))

lteqgt((1 to 10).toArray, 4)