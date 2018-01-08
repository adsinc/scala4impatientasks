val m = Map[String, Double](
  "Apple" -> 10,
  "Orange" -> 90
)

m.mapValues(_ * 1.1)