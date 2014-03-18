def sign(n: Int) = if(n > 0) 1 else if(n < 0) -1 else 0

for (i <- -2 to 2) println("sign(%d)=%d".format(i, sign(i)))