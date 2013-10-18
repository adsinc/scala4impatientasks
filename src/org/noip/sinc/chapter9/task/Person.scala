package org.noip.sinc.chapter9.task

import scala.collection.mutable.ArrayBuffer
import java.io.{FileInputStream, ObjectInputStream, FileOutputStream, ObjectOutputStream}

class Person(val name: String, val age: Int = 0) extends Serializable {
	private val friendList = new ArrayBuffer[Person]
	def addFiends(friends: Person*) { friendList ++= friends }
	override def toString: String = friendList.map(_.name).mkString("Person " + name + " is " + age + " years old. Friends:[", ", ", "]")
}

object PFTest extends App {
	val me = new Person("Alexey", 26)
	val sochi = new Person("So4i", 24)
	val yura = new Person("Yura", 27)
	val roma = new Person("Roma", 28)
	val oleg = new Person("Oleg", 28)
	val marina = new Person("Rome", 28)
	me.addFiends(sochi, yura, roma, oleg, marina)
	sochi.addFiends(me, oleg)
	yura.addFiends(me, marina)
	roma.addFiends(me, oleg)
	oleg.addFiends(me, sochi, roma)
	marina.addFiends(me)
	val persons = Array(me, sochi, yura, roma, oleg, marina)

	for(person <- persons) println(person)

	println("\nSerialization\n")

	val out = new ObjectOutputStream(new FileOutputStream("objects.txt"))
	out.writeInt(persons.length)
	for(person <- persons) out.writeObject(person)
	out.close()
	val in = new ObjectInputStream(new FileInputStream("objects.txt"))
	val read = for(i <- 0 until in.readInt()) yield in.readObject().asInstanceOf[Person]
	for(person <- read) println(person)
	in.close()

}