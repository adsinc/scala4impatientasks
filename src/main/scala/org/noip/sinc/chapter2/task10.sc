def pow(x: Long, n: Long): Double = {
  def isOdd(n: Long) = n % 2 == 0

  if (n > 0)
    if (isOdd(n)) {
      val y = pow(x, n / 2)
      y * y
    }
    else x * pow(x, n - 1)
  else
    if (n == 0) 1
    else 1 / pow(x, -n)
}
