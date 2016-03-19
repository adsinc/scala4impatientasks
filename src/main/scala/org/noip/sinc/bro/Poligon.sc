import scala.util.Random

type Money = Long

case class Operation(name: String, amount: Money)

val names = Seq("Aleksei", "Roman", "Oleg")

val maxOperations = 10

val operations = for(name <- names; i <- 0 until maxOperations)
  yield Operation(name, Random.nextInt(10))

def sumAmount(operations: Seq[Operation]) = operations.map(_.amount).sum

val totalPayed = sumAmount(operations)

val operationsByName = operations.groupBy(_.name)

val payedByName = operationsByName.map{case (name, ops) => (name, sumAmount(ops))}

def mapToStringSeq(map: Map[_, _]) = map map { case (name, value) => s"$name $value" }

val shouldBe = totalPayed * 1.0 / names.length

val diffByName = payedByName map {case (name, value) => (name, shouldBe - value)}

val res = Seq(
  mapToStringSeq(payedByName),
  "Diffs:",
  mapToStringSeq(diffByName),
  "-" * 10,
  s"Total: $totalPayed",
  s"Each must pay: $shouldBe"
)
println(res map {
  case seq: Seq[_] => seq mkString "\n"
  case s: String => s
} mkString "\n")
