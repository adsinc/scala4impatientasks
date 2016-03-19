val prices = List(10, 20, 40, 22)
val quantities = List(1, 2, 4, 2)

(prices zip quantities) map {p => p._1 * p._2}

val prod: (Int, Int) => Int = _ * _

prices zip quantities map prod.tupled