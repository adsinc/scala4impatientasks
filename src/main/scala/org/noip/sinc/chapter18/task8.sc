def printValues(f: { def apply(n: Int): Int },
								from: Int, to: Int) = {
	from to to foreach {x => println(f apply x)}
}
printValues((x: Int) => x * x, 3, 6)
printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)

