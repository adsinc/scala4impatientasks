val n = 10
val a = new Array[Int](n)
0 until n copyToArray(a)
a.mkString(",")