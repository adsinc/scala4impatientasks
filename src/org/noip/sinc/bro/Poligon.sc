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

def createTotal(payedByName: Map[String, Money]) = {
  def makeColumn(column: Seq[Any]) = {
    val cols = column.map(_.toString)
    val maxWidth = cols.map(_.length).max
    def normalise(value: String) =
      value + (" " * (maxWidth - value.length))
    cols.map(normalise)
  }
  val header = ("Name", "Payed")
  val table = payedByName
  makeColumn(payedByName.map(_.1))
  makeColumn(payedByName.map(_.2))
}

createTotal(payedByName)