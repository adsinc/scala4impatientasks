def adjustToPair(f: (Int, Int) => Int) =
  (p: Pair[Int, Int]) => f(p._1, p._2)

adjustToPair(_ * _)((6,7))

val pairs = (1 to 10) zip (11 to 20)
pairs map adjustToPair(_ + _)