//def pack[T](ls: List[T]): List[List[T]] = {
//  def loop(ls: List[T], acc: List[List[T]]): List[List[T]] = ls match {
//    case Nil => acc
//    case h :: t if h == acc.head.head => loop(t, (h :: acc.head) :: acc.tail)
//    case h :: t => loop(t, List(h) :: acc)
//  }
//  loop(ls.tail, List(ls.head) :: Nil).reverse
//}
//pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
def pack[T](ls: List[T]): List[List[T]] = {
  def loop(ls: List[T], acc: List[List[T]]): List[List[T]] = ls match {
    case Nil => acc
    case h :: t => loop(t dropWhile (_ == h), (ls takeWhile (_ == h)) :: acc)
  }
  loop(ls.tail, List()).reverse
}
pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))