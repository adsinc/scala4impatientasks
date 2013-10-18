package org.noip.sinc.chapter8

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 13:34
 */
class Creature {
	val range = 10
	var env: Array[Int] = new Array[Int](range)
	override def toString: String = getClass.getName + "[" + env.size + "]"
}

class Ant extends Creature {
	override val range = 2
}

class Bug extends {
	override val range = 3
} with Creature

object Test extends App {
	val creature = new Creature
	val ant = new Ant
	val bug = new Bug

	println(creature)
	println(ant)
	println(bug)
}