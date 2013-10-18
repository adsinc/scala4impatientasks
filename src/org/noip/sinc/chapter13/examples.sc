//13.17
//for(i <- (1 to 100).par) print(i + " ")
//for(i <- (1 to 100).par) yield i + " "
//13.15
//import scala.collection.JavaConversions._
//val props: scala.collection.mutable.Map[String, String] = System.getProperties
//13.12
//val powers = (1 until 1000).view.map(math.pow(10, _))
//powers(100)
//13.11
//import scala.io.Source
//def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)
//val tenOrMore = numsFrom(10)
//tenOrMore.tail.tail.tail
//val squares = numsFrom(1).map(x => x * x)
//squares.tail
//squares.take(5).force
//
//val words = Source.fromFile("words.txt").getLines().toStream
//words
//words(5)
//words
//13.10
//val prices = List(5, 20, 9.95)
//val quantities = List(10, 2, 1)
//prices zip quantities
//(prices zip quantities).map(p => p._1 * p._2)
//((prices zip quantities) map {p => p._1 * p._2}).sum
//List(5, 20, 9.95) zip List(10, 2)
//List(5, 20, 9.95) zipAll(List(10, 2), 0.0, 1)
//"Scala".zipWithIndex
//
//"Scala".zipWithIndex.max
//"Scala".zipWithIndex.max._2
//13.9
//List(1, 7, 2, 9).reduceLeft(_ - _)
//List(1, 7, 2, 9).reduceRight(_ - _)
//List(1, 7, 2, 9).foldLeft(0)(_ - _)
//(0 /: List(1, 7, 2, 9))(_ - _)
//List(1, 7, 2, 9).foldRight(0)(_ - _)
//import scala.collection.mutable
//val freq = mutable.Map[Char, Int]()
//for(c <- "Mississippi") freq(c) = freq.getOrElse(c, 0) + 1
//freq
//(Map[Char, Int]() /: "Mississippi") {
//	(m, c) => m + (c -> (m.getOrElse(c, 0) + 1))
//}
//"Mississippi".foldLeft(Map[Char, Int]())((m, c) => m + (c -> (m.getOrElse(c, 0) + 1)))
//(1 to 10).scanLeft(0)(_ + _)
//13.8
//val names = List("Peter", "Paul", "Mary")
//names.map(_.toUpperCase())
//def ulcase(s: String) = Vector(s.toUpperCase(), s.toLowerCase())
//names.map(ulcase)
//names.flatMap(ulcase)
//"-3+4".collect { case '+' => 1; case '-' => -1}
//names.foreach(println)
//import scala.collection.mutable.ArrayBuffer
//13.7
//Vector(1, 2, 3) :+ 5
//0 +: Vector(1, 2, 3)
//val numbers = ArrayBuffer(1, 2, 5)
//numbers += 3
//var numbers = Set(1, 2, 3)
//numbers += 5
//numbers
//var numberVector = Vector(1, 2, 3)
//numberVector :+= 5
//numberVector
//Set(1, 2, 3) - 2

//13
//def digits(n: Int): Set[Int] =
//	if(n < 0) digits(-n)
//	else if(n < 10) Set(n)
//	else digits(n / 10) + n % 10
//digits(12345)
////13.4
//val dig = List(1, 3, 4)
//10 :: dig
//1 :: 3 :: 4 :: Nil
//def sum(list: List[Int]): Int = if(list == Nil) 0 else list.head + sum(list.tail)
//sum(dig)
//def sum1(list: List[Int]): Int = list match {
//	case Nil => 0
//	case h :: t => h + sum1(t)
//}
//sum1(dig)
//dig.sum
//13.5
//val lst = scala.collection.mutable.LinkedList(1, -2, 7, -9)
//var cur = lst
//while(cur != Nil) {
//	if(cur.elem < 0) cur.elem = 0
//	cur = cur.next
//}
//lst
//var cur = lst
//while(cur != Nil && cur.next != Nil) {
//	cur.next = cur.next.next
//	cur = cur.next
//}
//lst
//13.6
//val digits = Set(1, 7, 2, 9)
//digits.contains(0)
//Set(1, 2).subsetOf(digits)
//val primes = Set(2, 3, 5, 7)
//digits union primes
//digits & primes
//digits -- primes
