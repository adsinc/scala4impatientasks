//17
val f: PartialFunction[Char, Int] = { case '+' => 1; case '-' => -1 }
f('-')
f.isDefinedAt(0)
//f(0)
"+1-2".collect(f)
"+1-2".collect{ case '+' => 1; case '-' => -1 }
//15
//sealed abstract class TrafficLightColor
//case object Red extends TrafficLightColor
//case object Green extends TrafficLightColor
//case object Yellow extends TrafficLightColor

//14
//sealed abstract class Amount
//case class Dollar(value: Double) extends Amount
//case class Currency(value: Double, unit: String) extends Amount
//12
//abstract class Item
//case class Product(description: String, price: Double) extends Item
//case class Bundle(description: String, discount: Double,items: Item*) extends Item
//val bundle = Bundle("Father's day special", 20,
//	Product("Scala for impatient", 39.95),
//	Bundle("Anchor Distillery Sampler", 10,
//	 	Product("Old Potrero Straight Rye Whiskey", 79.95),
//	 	Product("Junipero Jin", 32.95)
//	)
//)
//
//
//def price(it: Item): Double = it match {
//	case Product(_, p) => p
//	case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
//}
//price(bundle)
//10
//abstract class Amount
//case class Currency(value: Double, unit: String) extends Amount
//val amt = new Currency(10, "EUR")
//val price = amt.copy()
//val price = amt.copy(value = 9.99)
//val price = amt.copy(unit = "RUB")
//9
//abstract class Amount
//case class Dollar(value: Double) extends Amount
//case class Currency(value: Double, unit: String) extends Amount
//case object Nothing extends Amount
////val amt: Amount = new Dollar(10)
////val amt: Amount = new Currency(10, "E")
//val amt: Amount = Nothing
//amt match {
//	case Dollar(v) => "$" + v
//	case Currency(_, u) => "Oh noes, I got " + u
//	case Nothing => ""
//}
//8
//import scala.collection.JavaConversions.propertiesAsScalaMap
//for((k, v) <- System.getProperties)
//	println(k + " -> " + v)
//for((k, "") <- System.getProperties)
//	println(k)
//for((k, v) <- System.getProperties if v == "")
//	println(k)
//7
//val (q, r) = BigInt(10) /% 3
//5
//val arr = Array[Int](0, 4, 9)
//arr match {
//	case Array(0) 		=> "0"
//	case Array(x, y)	=> x + " " + y
//	case Array(0, _*)	=> "0 ..."
//	case _ 				=> "Something else"
//}
//val lst = List(0)
//lst match {
//	case 0 :: Nil 		=> "0"
//	case x :: y :: Nil	=> x + " " + y
//	case 0 :: tail	=> "0 ..."
//	case _ 				=> "Something else"
//}
//val pair = (1, 0)
//pair match {
//	case (0, _)	=> "0 ..."
//	case (y, 0)	=> y + " 0"
//	case _ 		=> "neither is 0"
//}
//4
//val obj = 1
//obj match {
//	case x: Int		=> x
//	case s: String	=> Integer.parseInt(s)
//	case v: BigInt 	=> Int.MaxValue
//	case _ 			=> 0
//}
//3
//val str = "0123"
//str(3) match {
//	case '+' => 1
//	case '-' => -1
//	case ch if Character.isDigit(ch) => Character.digit(ch, 10)
//}
//2
//val c = '1'
//c match {
//	case '+' => 1
//	case '-' => -1
//	case _ if Character.isDigit(c) => Character.digit(c, 10)
//	case _ => 0
//}
// 1
//val c = '-'
//c match {
//	case '+' => 1
//	case '-' => -1
//	case _ => 0
//}
