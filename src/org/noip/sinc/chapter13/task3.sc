def removeZeros1(lst: List[Int]) = {
  def loop(xs: List[Int], acc: List[Int]): List[Int] = xs match {
    case Nil => acc
    case 0 :: t => loop(t, acc)
    case h :: t => loop(t, acc ::: List(h))
  }
  loop(lst, List())
}

def removeZeros2(lst: List[Int]) = lst.filterNot(_ == 0)

def removeZeros3(lst: List[Int]) = (lst :\ List[Int]()) {
  (n, list) => if(n == 0) list else n :: list
}

val list = List(0, 1, 0, 2, 0, 3, 0, 0)

removeZeros1(list)
removeZeros2(list)
removeZeros3(list)