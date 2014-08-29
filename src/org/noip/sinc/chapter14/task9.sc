def sum1(xs: List[Option[Int]]) = (xs map (_.getOrElse(0))).sum
val l = None :: ((1 to 10) map (Option(_))).toList
sum1(l)