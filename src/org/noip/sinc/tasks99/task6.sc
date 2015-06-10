def isPalindrome[T](ls: List[T]): Boolean =
  ls == ls.reverse
isPalindrome(List(1, 2, 3, 2, 1))