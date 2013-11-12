package org.noip.sinc.chapter22.tasks

import scala.io.Source
import scala.util.continuations._


object Task2 extends App{
	var cont: (String => String) = null
	var fileName = "1.txt"
	var contents = ""

	reset {
		while(contents == "") {
			try{
				contents = Source.fromFile(shift { k: (String => String) => cont = k }, "UTF-8").mkString
			} catch { case _ => ""}
		}
	}

	if(contents == "") {
		println("Try another filename: ")
		fileName = readLine()
		cont(fileName)
	}
	println(contents)
}
