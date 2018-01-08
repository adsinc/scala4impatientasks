package org.noip.sinc.chapter4

object Task7 extends App {
  val env = sys.props
  val maxLen = env.keys.map(_.length).max
  env map { case (k, v) =>
    s"$k${" " * (maxLen - k.length)}|$v"
  } foreach println
}
