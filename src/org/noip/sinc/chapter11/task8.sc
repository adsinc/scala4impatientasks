class Matrix(val data: List[List[Int]]) {
  val n = data.length

  def apply(row: Int, col: Int) = data(row)(col)

  def + (b: Matrix) = new Matrix(
    (data zip b.data).map(p => (p._1 zip p._2).map(x => x._1 + x._2))
  )

  def * (b: Matrix) = ???

  def trans = new Matrix(???)

  def * (x: Int) = new Matrix(data map {_ map {_ * x}})

  override def toString: String = data map {_ mkString "\t"} mkString ("\n", "\n", "\n")
}

val a = new Matrix(List(
  List(1, 2),
  List(3, 4)
))

val b = new Matrix(List(
  List(5, 6),
  List(7, 8)
))

a * 2
a + b

