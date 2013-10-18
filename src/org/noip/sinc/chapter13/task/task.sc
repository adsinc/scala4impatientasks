import scala.io.Source
import scala.collection.JavaConversions._

//9
val fileNames = Array("bear.txt", "exps.txt", "ls.txt", "hello.txt", "numbers.txt")



def calculateFrequencies(names: Array[String]) = {
	val frequencies: scala.collection.mutable.ConcurrentMap[Char, Int]  = new java.util.concurrent.ConcurrentHashMap[Char, Int]()
	names.par.foreach {
		Source.fromFile(_).foreach((c) => frequencies(c) = frequencies.getOrElse(c, 0) + 1)
	}
	frequencies.values.sum
}


val result = calculateFrequencies(fileNames)
var flag = false
var i = 0
while(!flag && i != 1000) {
	val res = calculateFrequencies(fileNames)
	println(res)
	if(result != res) flag = true else {}
	i += 1
}
////1
//def indexes(str: String): Map[Char, SortedSet[Int]] = {
//	(Map[Char, SortedSet[Int]]() /: str.zipWithIndex) {
//		(map, pair) => map + (pair._1 -> (map.getOrElse(pair._1, SortedSet[Int]()) + pair._2))
//	}
//}
//indexes("Mississippi rules")
//2
//def indexes(str: String): Map[Char, List[Int]] = {
//	(Map[Char, List[Int]]() /: str.zipWithIndex) {
//		(map, pair) => map + (pair._1 -> (map.getOrElse(pair._1, List[Int]()) :+ pair._2))
//	}
//}
//indexes("Mississippi rules")
//3
//def removeZero(list: List[Int]): List[Int] = list match {
//	case Nil => Nil
//	case h :: t => if(h != 0) h :: removeZero(t) else removeZero(t)
//}
//val l = List(0, 2, -1, 23, 0, 2, 0, 0, 1)
//removeZero(l)
//4
//def f(a: Array[String], m: Map[String, Int]) = {
//	a.flatMap(m.get(_))
//}
//val a = Array("Tom", "Fred", "Harry")
//val m = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
//f(a, m)
//5
//def mkStr[A](col: Seq[A], left: String, delim: String, right: String): String = {
//	left + col.map(_.toString).reduceLeft(_ + delim + _) + right
//}
//mkStr(List(0, 2, -1, 23, 0, 2, 0, 0, 1), "{",  "|", "}")
//6
//val lst = List(1, 3, 7, 10, 0)
////создание копии списка
//(lst :\ List[Int]())(_ :: _)
//(lst :\ List[Int]())((l, r) => r :+ l)
////создание копии списка
//(List[Int]() /: lst)(_ :+ _)
//(List[Int]() /: lst)((l, r) => r :: l)
//7
//val prices = List(5, 20, 9.95)
//val quantities = List(10, 2, 1)
//prices zip quantities
//(prices zip quantities) map (p => p._1 * p._2)
//val f = Function.tupled((x:Double, y:Int) => x * y)
//(prices zip quantities) map (f(_))
//8
//def asMatrix(arr: Array[Double], colNum: Int) = arr.grouped(colNum).toArray
//asMatrix(Array[Double](1, 2, 3, 4, 5, 6), 3)