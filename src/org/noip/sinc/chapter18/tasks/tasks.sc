import scala.Array

/**
 * @author dolgiy
 */
//9

//8
//def printValues(f: (Int) => Int, from: Int, to: Int) {
//	print((from to to).map(f(_)).mkString(", "))
//}
//printValues((x) => x * x, 3, 6)
//printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)
//7
//def met(obj: { def close(): Unit }, handler: { def close(): Unit } => Unit) = {
//	try {
//		handler(obj)
//	} finally {
//		obj.close()
//	}
//}
//class Closed {
//	def close() {
//		println("closed")
//	}
//}
//class NotClosed
//val c = new Closed
//val n = new NotClosed
//met(c, (x) => { x.close() })
//met(n, (x) => { x.close() })
//6
//def func(a: Array[Int], i: Int): Int = if(a.head >= i) 0 else 1 + func(a.tail, i)
//val arr = Array(1, 2, 4, 5, 11, 20, 100)
//for(i <- 0 to 30) println("indexOf %d=%d".format(i, func(arr, i)))
//5
//import scala.collection.mutable.ArrayBuffer
//class Network {
//	class Member(val name: String) {
//		val contacts = new ArrayBuffer[Member]
//	}
//
//	private val members = new ArrayBuffer[Member]
//
//	def join(name: String) = {
//		val m = new Member(name)
//		members += m
//		m
//	}
//}
//type NetworkMember = n.Member forSome { val n: Network}
//def process(m1: NetworkMember, m2: NetworkMember) = (m1, m2)
////def process[M <: n.Member forSome {val n: Network}](m1: M, m2: M) = (m1, m2)
//val chatter = new Network
//val myFace = new Network
//val fred = chatter.join("Fred")
//val wilma = chatter.join("Wilma")
//val barney = myFace.join("Barney")
//process(fred, wilma)
//process(fred, barney)
//3
//object Title
//object Author
//class Document {
//	var title = ""
//	var author = ""
//	private var useNextArgsAs: Any = null
//	def set(obj: Title.type): this.type = { useNextArgsAs = obj; this }
//	def set(obj: Author.type): this.type = { useNextArgsAs = obj; this }
//	def to(arg: String) = {
//		useNextArgsAs match {
//			case a: Title.type 	=> title = arg
//			case a: Author.type => author = arg
//			case _ 				=>
//		}
//		this
//	}
//	override def toString: String = "title=%s, author=%s".format(title, author)
//}
//val book = new Document
//book set Title to "Scala for the impatient" set Author to "Cay Horstmann"
//2
//object then
//object around
//object show
//class Bug {
//	var isForward = true
//	var pos = 0
//	def move(n: Int) = { pos += (if(isForward) n else -n); this }
//	def turn() = { isForward = !isForward; this }
//	def turn(obj: around.type): this.type = { turn(); this }
//	def and(obj: then.type): this.type = { this }
//	def and(obj: show.type): this.type = { println(pos); this }
//}
//val bug = new Bug
//// 4 10 5
//bug move 4 and show and then move 6 and show turn around move 5 and show
// 1
//class Bug {
//	var isForward = true
//	var pos = 0
//	def move(n: Int) = { pos += (if(isForward) n else -n); this }
//	def turn() = { isForward = !isForward; this }
//	def show() = { println(pos); this }
//}
//val bug = new Bug
//// 4 10 5
//bug.move(4).show().move(6).show().turn().move(5).show()

