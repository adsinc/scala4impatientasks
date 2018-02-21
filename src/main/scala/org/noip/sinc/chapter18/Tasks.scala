package org.noip.sinc.chapter18

object Tasks {

  class Pair[S, T](val first: S, val second: T) {
    def swap: Pair[T, S] = new Pair(second, first)
  }

  object Pair {
    def swap[S, T](p: Pair[S, T]): Pair[T, S] =
      new Pair(p.second, p.first)
  }

  class MutablePair[T](var first: T, var second: T) {
    def swap: MutablePair[T] = {
      val tmp = first
      first = second
      second = tmp
      this
    }
  }


}
