package org.noip.sinc.chapter22.examples

import scala.io.Source
import scala.util.continuations._

/**
 * Created with IntelliJ IDEA.
 * Date: 04.11.13
 * Time: 20:13
 * @author dolgiy
 */
object TestExample6 extends App{

	def tryRead(): Unit @cps[Unit] = {
		while(contents == "") {
			try{
				contents = Source.fromFile(fileName, "UTF-8").mkString
			} catch { case _ => ""}
			shift { k: (Unit => Unit) =>
				cont = k
			}
		}
	}

	var cont: (Unit => Unit) = null
	var fileName = "13.1.txt"
	var contents = ""

	reset {
		tryRead()
	}

	if(contents == "") {
		println("Try another filename: ")
		fileName = readLine()
		cont()
	}
	println(contents)
}
