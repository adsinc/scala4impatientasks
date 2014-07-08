def factorial(n: Int) = 1 to n reduceLeft (_ * _)

factorial(1)
factorial(2)
factorial(3)
factorial(4)
factorial(5)
factorial(6)