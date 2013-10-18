//import scala.collection.immutable.HashMap
//import scala.collection.mutable
//import scala.io.Source
//val str = Source.fromFile("13.10.txt").mkString
//
//def printMillis(msg: String)(code: => Unit) {
//	val start = System.currentTimeMillis()
//	code
//	println("%s.\nWorking %d ms".format(msg, System.currentTimeMillis() - start))
//}
//
//printMillis("Using mutable HashMap") {
//	val frequencies = new mutable.HashMap[Char, Int]()
//	for(c <- str.par) frequencies(c) = frequencies.getOrElse(c, 0) + 1
//	println(frequencies.toSeq.sorted)
//}
//printMillis("Using immutable HashMap") {
//	val freq = str.map((_, 1)).groupBy(_._1).map(x => x._1 -> x._2.length)
////	println(freq.toSeq.sorted)
//}
//printMillis("Using immutable parallel collection") {
//	val freq = str.par.aggregate(new HashMap[Char, Int])(
//		(map, ch) => map + (ch -> (map.getOrElse(ch, 0) + 1)),
//		(map1, map2) => map1 ++ map2.map{ case (k, v) => k -> (v + map1.getOrElse(k, 0)) }
//	)
////	println(frequencies.toSeq.sorted)
//}
//

val str = io.Source.fromFile("13.10.txt").mkString


def printMills(msg: String)(block: => Unit) {
	val start = System.currentTimeMillis()
	block
	val end = System.currentTimeMillis()
	println(msg.format(end-start))
}

printMills("Using mutable collection: %d ms") {
	val freq = new collection.mutable.HashMap[Char, Int]
	for (c <- str) freq(c) = freq.getOrElse(c, 0) + 1
//	println(freq.toSeq.sorted)
}
printMills("Using mutable parallel collection: %d ms") {
	val freq = str.par.aggregate(new collection.immutable.HashMap[Char, Int])(
		(x, c) => x + (c ->(x.getOrElse(c, 0) + 1)),
		(map1, map2) => map1 ++ map2.map{ case (k,v) => k -> (v + map1.getOrElse(k,0)) }
	)

//	println(freq.toSeq.sorted)
}
printMills("Using immutable collection: %d ms") {
	val freq = str.map(c => (c, 1)).groupBy(_._1).map(x => (x._1, x._2.length))
//	println(freq.seq.toSeq.sorted)
}










