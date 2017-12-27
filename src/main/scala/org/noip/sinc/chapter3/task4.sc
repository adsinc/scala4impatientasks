import scala.util.Random

val as = Array.fill(10)(Random.nextInt())
as.filter(_ > 0) ++ as.filterNot(_ > 0)