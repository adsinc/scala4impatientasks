def midle[T](it: Iterable[T]): T = (it drop (it.size / 2)).head
midle("world")
midle(Array(1, 2, 3))
midle(Array(1, 2, 3, 4))
midle(Array(1, 2, 3, 4, 5))
midle(Array(1, 2, 3, 4, 5, 6))