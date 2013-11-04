package org.noip.sinc.chapter22.examples

import scala.util.continuations._

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample2 extends App{
	var cont: (Int => Double) = null
	var fileName = "13.1.txt"
	var contents = ""

	reset {
		0.5 * shift { k: (Int => Double) => cont = k } + 1
	}
	println(cont(1))
	println(cont(2))
}
