sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

def leafSum(tree: BinaryTree): Int = tree match {
  case Leaf(v) => v
  case Node(l, r) => leafSum(l) + leafSum(r)
}

val lst = Node(Node(Leaf(3), Leaf(8)), Node(Leaf(9), Node(Leaf(2), Leaf(4))))
leafSum(lst)