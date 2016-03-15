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

val data = payedByName map { case (name, value) => s"$name $value"} mkString "\n"

val shouldBe = totalPayed * 1.0 / names.length

val res = Seq(
  data,
  "-" * 10,
  s"Total: $totalPayed",
  s"Each must pay: $shouldBe"
)
println(res mkString "\n")
