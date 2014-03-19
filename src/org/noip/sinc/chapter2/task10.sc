def pow(i: Long, n: Long): Double = n match {
	case 0 => 1.0
	case k if k > 0 =>
		if(k % 2 == 0) pow(i, k/2) * pow(i, k/2)
		else i * pow(i, k - 1)
	case k => 1.0 / pow(i, -k)
}
for(i <- -3 to 3; j <- -3 to 3 if i < -1 || i > 1)
	println("%d^%d=%f ".format(i, j, pow(i, j)))





























