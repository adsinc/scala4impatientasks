package org.noip.sinc.chapter22.examples

import scala.util.continuations._

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample3 extends App{
	var cont: (Unit => Unit) = null
	reset {
		println("Before shift")
		shift {
			k: (Unit => Unit) => {
				cont = k
				println("Inside shift")
			}
		}
		println("After shift")
	}
	println("After reset")
	cont()
}
