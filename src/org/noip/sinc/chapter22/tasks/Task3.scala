package org.noip.sinc.chapter22.tasks

import scala.util.continuations._
import java.io.File

/**
 * @author dolgiy
 */
object Task3 extends App {
	val dir = new File("/home/sinc/scala")
	val it = new FileIterator(dir)
	for(i <- 1 to 10) it.next
}

class FileIterator(dir: File) {

	var cont: (Unit => Unit) = null

	reset{
		processDirectory(dir)
	}

	def next = cont()

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
}
