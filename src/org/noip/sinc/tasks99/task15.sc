def duplicateN[T](n: Int, ls: List[T]): List[T] =
  ls flatMap {e =>
    1 to n map (_ => e)}
duplicateN(3, List('a, 'b, 'c, 'c, 'd))