//15.6.1
//object Util {
//	def sum(xs: Seq[Int]): BigInt = {
//		if(xs.isEmpty) 0 else xs.head + sum(xs.tail)
//	}
//
//	def sum2(xs: Seq[Int], partial: BigInt): BigInt = {
//		if(xs.isEmpty) partial else sum2(xs.tail, xs.head + partial)
//	}
//}
//Util.sum2(1 to 1000000, 0)
import scala.util.control.TailCalls._
object Util {
	def evenLength(xs: Seq[Int]): TailRec[Boolean] =
		if(xs.isEmpty) done(true) else tailcall(oddLength(xs.tail))
	def oddLength(xs: Seq[Int]): TailRec[Boolean] =
		if(xs.isEmpty) done(false) else tailcall(evenLength(xs.tail))
}
Util.evenLength(1 to 1000000).result