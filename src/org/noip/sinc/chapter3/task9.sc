val res = java.util.TimeZone.getAvailableIDs
	.filter(_ startsWith "America")
	.map(_.replace("America/", ""))
	.sorted
println(res mkString "\n")




































