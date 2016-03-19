def indexes(s: String) = (Map[Char, List[Int]]() /: (s zip (0 until s.length))) {
  (m, p) => m + (p._1 -> (m.getOrElse(p._1, List[Int]()) :+ p._2))
}
indexes("Mississippi").mkString

