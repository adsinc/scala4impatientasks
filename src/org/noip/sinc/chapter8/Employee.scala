package org.noip.sinc.chapter8

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 12:39
 */
class Employee(name: String, age: Int, val salary: Double = 0) extends Person(name, age){

}

abstract class Person(val name: String, val age: Int = 0){
	override def toString: String = getClass.getName + "[name=" + name + "]"
	def id: Int = 1
}

class SecretAgent(codeName: String) extends Person(codeName) {
	override val name = "secret"
	override def toString: String = "secret"
}

class Student(override val id:Int) extends Person("")

object Test1 extends App{
	val p = new Employee("Alex", 26)
	println(p)
	val secretAgent = new SecretAgent("007")
	println(secretAgent)
}