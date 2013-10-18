//10
def compose(f1: (Double) => Option[Double], f2: (Double) => Option[Double])= {
	(x: Double) => f1(x) match {
		case None => None
		case _ => f2(x) match {
			case None => None
			case _ => Some(1)
		}
	}
}


def f(x : Double) = if(x >= 0) Some(math.sqrt(x)) else None
def g(x : Double) = if(x != 1) Some(1 / (x - 1)) else None
val h = compose(f, g)
h(2)
h(1)
h(0)
h(-1)
//9
//val lst: List[Option[Int]] = List(Some(1), None, Some(2), Some(3), None)
//def sumNonEmpty(lst: List[Option[Int]]): Int = lst.map(_.getOrElse(0)).sum
//sumNonEmpty(lst)
//8
//sealed abstract class BinaryTree
//case class Leaf(value: Int) extends BinaryTree
//case class Node(op: Char, elems: BinaryTree*) extends BinaryTree
//def eval(tree: BinaryTree): Int = tree match {
//	case Leaf(v) => v
//	case Node(op, els @ _*) => op match{
//		case '+' => els.map(eval _).sum
//		case '-' => - els.map(eval _).sum
//		case '*' => els.map(eval _).product
//	}
//}
//val tree = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))
//eval(tree)
//7
//sealed abstract class BinaryTree
//case class Leaf(value: Int) extends BinaryTree
//case class Node(elems: BinaryTree*) extends BinaryTree
//def leafSum(tree: BinaryTree): Int = tree match {
//	case Leaf(v) => v
//	case Node(els @ _*) => els.map(leafSum _).sum
//}
//val tree = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
//
//leafSum(tree)
//6
//sealed abstract class BinaryTree
//case class Leaf(value: Int) extends BinaryTree
//case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
//def leafSum(tree: BinaryTree): Int = tree match {
//	case Leaf(v) => v
//	case Node(l, r) => leafSum(l) + leafSum(r)
//}
//val tree = Node(Leaf(2), Leaf(1))
//leafSum(tree)
//5
//val tree = List(List(3, 8), 2, List(5))
//def leafSum(tree: List[Any]): Int = tree match {
//	case Nil => 0
//	case (head: List[Any]) :: tail => leafSum(head) + leafSum(tail)
//	case (head: Int) :: tail => head + leafSum(tail)
//	case _ => 0
//}
//leafSum(tree)
//4
//abstract class Item
//case class Product(description: String, price: Double) extends Item
//case class Bundle(description: String, discount: Double,items: Item*) extends Item
//case class Multiple(count: Int, item: Item) extends Item
//def price(it: Item): Double = it match {
//	case Product(_, p) => p
//	case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
//	case Multiple(count, item) => price(item) * count
//}
//
//val bundle = Bundle("Father's day special", 20,
//Multiple(10, Product("Scala for impatient", 39.95)),
//Multiple(2, Bundle("Anchor Distillery Sampler", 10,
//Product("Old Potrero Straight Rye Whiskey", 79.95),
//Product("Junipero Jin", 32.95)
//)
//)
//)
//
//
//
//price(bundle)
//val b = Multiple(2, Bundle("sell", 10, Product("1", 30)))
//price(b)
//3
//def swap(arr: Array[Int]) = arr match {
//	case Array(x, y, rest @ _*) => Array(y, x) ++ rest
//	case _ => arr
//}
//val a = Array(1, 2)
//swap(a)
//2
//def swap(pair: Pair[Int, Int]) = pair match {
//	case (l, r) => (r, l)
//	case _ => None
//}
//swap((1, 2))