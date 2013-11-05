package org.noip.sinc.chapter22.examples

import scala.io.Source
import scala.util.continuations._
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample7 extends App{
	var cont: (Unit => Unit) = null

	def processDirectory(dir: File): Unit @cps[Unit] = {
		val files = dir.listFiles
		var i = 0
		while(i < files.length) {
			val f = files(i)
			i += 1
			if(f.isDirectory)
				processDirectory(f)
			else {
				shift {
					k: (Unit => Unit) => {
						cont = k
					}
				}
				println(f)
			}
		}
	}
	val dir = new File("/home/sinc/scala")
	reset{
		processDirectory(dir)
	}
	for(i <- 1 to 10) cont()
}
