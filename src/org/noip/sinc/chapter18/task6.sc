def func(a: Array[Int], n: Int): Int Either Int = {
	val i = a indexWhere (_ >= n)
	if(a(i) == n) Right(i)
	else if(i == 0) Left(i)
	else Left(Seq(i - 1, i) maxBy a)
}
func(Array(1, 2, 4, 222, 1234), 220)
func(Array(1, 2, 4, 222, 1234), 10)
func(Array(1, 2, 4, 222, 1234), 1)
func(Array(1, 2, 4, 222, 1234), 2)
func(Array(1, 2, 4, 222, 1234), 3)
func(Array(1, 2, 4, 222, 1234), 100)
func(Array(1, 2, 4, 222, 1234), 222)
func(Array(1, 2, 4, 222, 1234), 223)