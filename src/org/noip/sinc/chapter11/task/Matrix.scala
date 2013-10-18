package ch11.task

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 14.06.13
 * Time: 14:55
 */
class Matrix(val row: Int, val col: Int) {
	private val a: Array[Array[Int]] = Array.ofDim[Int](row, col)

	def this(values: Array[Array[Int]]) {
		this(values.length, values.map(_.length).max)
		for(i <- 0 until row; j <- 0 until col; if(values(i).length > j)) {
			this(i, j) = values(i)(j)
		}
	}

	def apply(row: Int, col: Int) = a(row)(col)

	def update(row: Int, col: Int, value: Int) = {
		a(row)(col) = value
		value
	}

	def +(b: Matrix): Matrix = {
		if(b.row == row && b.col == col) {
			val result = new Matrix(row, col)
			for(i <- 0 until row; j <- 0 until col) result(i, j) = this(i, j) + b(i, j)
			result
		} else throw new IllegalArgumentException
	}

	def *(b: Matrix): Matrix = {
		if(col == b.row) {
			val result = new Matrix(row, b.col)

			for(i <- 0 until result.row; j <- 0 until result.col)
				result(i, j) = (for(k <- 0 until col) yield this(i, k) * b(k, j)).sum

			result
		} else throw new IllegalArgumentException
	}

	def *(s: Int): Matrix = {
		val result = Matrix(2, 2)
		for(i <- 0 until row; j <- 0 until col) result(i, j) = this(i, j) * s
		result
	}

	override def toString: String = a.map(_.mkString("\t")).mkString("-" * 10 + "\n","\n", "\n" + "-" * 10)
}

object Matrix {
	def apply(row: Int, col: Int) = new Matrix(row, col)
	def apply(values: Array[Array[Int]]) = new Matrix(values)
}

object MatrixTest extends App{
	val m1 = Matrix(Array(
		Array(1, 2),
		Array(3, 4)
	))
	val m2 = Matrix(Array(
		Array(1, 0),
		Array(0, 1)
	))
	val m3 = Matrix(Array(
		Array(1, 0, 0),
		Array(0, 1, 0),
		Array(0, 0, 1)
	))
	val m4 = Matrix(Array(
		Array(1, 2, 3),
		Array(4, 5, 6),
		Array(7, 8, 9)
	))

	val m5 = Matrix(Array(
		Array(1, 0),
		Array(0, 1),
		Array(0, 0)
	))
	val m6 = Matrix(Array(
		Array(1, 2, 3),
		Array(4, 5, 6)
	))

	println(m1 * 2)
	println(m1 + m2)
	println(m1 * m2)
	println(m3 * m4)
	println(m5 * m6)
}
