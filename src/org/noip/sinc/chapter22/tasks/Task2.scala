package org.noip.sinc.chapter22.tasks

import scala.io.Source
import scala.util.continuations._


object Task2 extends App{
	var cont: (String => String) = null
	var contents = ""

	reset[String, Unit] {
		val fileName = shift { k: (String => String) => cont = k ; () }
		try {
				contents = Source.fromFile(fileName, "UTF-8").mkString
		} catch { case _: Throwable => ""}
		contents
	}

	if(contents == "") {
		println("Try another filename: ")
		cont(readLine())
	}
	println(contents)
}
