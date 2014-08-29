type Operator = (Int, Int) => Int

def add: Operator = _ + _
def sub: Operator = _ - _
def mul: Operator = _ * _

sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(op: Operator, children: BinaryTree*) extends BinaryTree

def eval(tree: BinaryTree): Int = tree match {
  case Leaf(v) => println(v); v
  case Node(op, children @ _*) => {
		val (zero, seq) = if(children.length == 1) 0 -> children
		else eval(children.head) -> children.tail
		(zero /: seq) {
			(acc, node) => op(acc, eval(node))
		}
	}
}
val lst = Node(add, Node(mul, Leaf(3), Leaf(8)), Leaf(2), Node(sub, Leaf(5)))
eval(lst)
