def factorial(n: Int) =
  if(n < 1) throw new IllegalArgumentException
  else 1 to n reduceLeft (_ * _)

factorial(1)
factorial(2)
factorial(3)
factorial(4)
factorial(5)
factorial(6)