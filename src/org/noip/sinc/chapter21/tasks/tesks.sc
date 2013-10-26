//4

//3
//import scala.language.implicitConversions
//import scala.language.postfixOps
//class FactorOps(val x: Int) {
//	def ! = 1 to x reduceLeft(_ * _)
//}
//implicit def int2FactorOps(x: Int) = new FactorOps(x)
//5!
//2
//class PercentOperations(val x: Int) {
//	def +%(percent: Int) = x + x * percent / 100
//}
//implicit def numericToPercentOperations(x: Int):  PercentOperations = new PercentOperations(x)
//120 +% 10
//1
//42 -> "Hello"
//"Hello" -> 42

