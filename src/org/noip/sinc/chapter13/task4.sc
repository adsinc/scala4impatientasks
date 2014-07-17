def extractIndexes(strs: Seq[String], mp: Map[String, Int]) =
  strs filter (mp contains) flatMap (mp get)

val strings = Array("Tom", "Fred", "Harry")
val map = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
extractIndexes(strings, map)