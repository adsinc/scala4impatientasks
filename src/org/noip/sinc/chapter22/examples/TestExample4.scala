package org.noip.sinc.chapter22.examples

import scala.util.continuations._

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample4 extends App{
	val result = reset {
		if(scala.util.Random.nextBoolean()) {
			shift {
				k: (String => String) => {
					"Exit"
				}
			}
		} else "End"
	}
	println(result)
}
