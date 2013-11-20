package org.noip.sinc.chapter22.tasks

import scala.util.continuations._

/**
 * @author dolgiy
 */
object Task5 extends App {
	var cont: Unit => String = null
	val a = "Mary was a little lamb".split(" ")
	reset {
		var i = 0
		while(i < a.length) {
			shift {
				k: (Unit => String) => {
					cont = k
					a(i)
				}
			}
			i += 1
		}
	}
	println(cont())
	println(cont())
}
