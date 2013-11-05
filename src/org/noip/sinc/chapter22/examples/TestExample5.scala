package org.noip.sinc.chapter22.examples

import scala.util.continuations._

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample5 extends App{
	val result = reset[Any, Any] {
		if(scala.util.Random.nextBoolean()) {
			shift {
				k: (Unit => Any) => {
					"Exit"
				}
			}
		} else "End"
	}
	println(result)
}
