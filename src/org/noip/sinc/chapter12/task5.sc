def largest(fun: Int => Int, input: Seq[Int]) =
  input.foldLeft(fun(input.head))((a, b) => math.max(a, fun(b)))

largest(x => 10 * x - x * x, 1 to 10)
largest(x => 10 * x - 2 * x * x, 1 to 10)