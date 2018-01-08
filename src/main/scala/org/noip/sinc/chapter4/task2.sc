import scala.io.Source

val path = "/Users/sinc/IdeaProjects/scala4impatientasks/words"

Source.fromFile(path)
  .getLines()
  .flatMap(_.split("[ ,()]+"))
  .toList
  .groupBy(identity)
  .mapValues(_.size)