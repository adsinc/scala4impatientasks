package org.noip.sinc.chapter15.tasks.t4


/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.07.13
 * Time: 13:21
 */
class CallFromJava {
	@varargs def sum(numbers: Int*) = numbers.sum
	def readFileContent(path: String) = Source.fromFile(path).mkString
}
