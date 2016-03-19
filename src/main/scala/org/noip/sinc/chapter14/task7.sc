sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(children: BinaryTree*) extends BinaryTree

def leafSum(tree: BinaryTree): Int = tree match {
  case Leaf(v) => v
  case Node(children @ _*) => (0 /: children)(_ + leafSum(_))
}

val lst = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
leafSum(lst)