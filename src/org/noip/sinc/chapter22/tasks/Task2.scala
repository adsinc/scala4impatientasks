package org.noip.sinc.chapter22.tasks

import scala.io.Source
import scala.util.continuations._
import java.io.FileNotFoundException


object Task2 extends App{

	def readFile(): String @cpsParam[String, Unit]= {
		val fileName = shift { k: (String => String) => cont = k ; () }
		Source.fromFile(fileName, "UTF-8").mkString
	}

	var cont: (String => String) = null
	var contents = ""

	try {
		reset[String, Unit] {
			contents = readFile()
			contents
		}
	} catch {
		case _ : FileNotFoundException => contents = ""
	}

	while(contents == "") {
		println("Try another filename: ")
		cont(readLine())
	}
	println(contents)
}
