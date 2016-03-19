def largestAt(fun: Int => Int, input: Seq[Int]) =
  input.foldLeft(input.head)((a, b) => if(fun(a) > fun(b)) a else b)

largestAt(x => 10 * x - x * x, 1 to 10)
largestAt(x => 10 * x - 2 * x * x, 1 to 10)