package org.noip.sinc.chapter18

import scala.collection.mutable.ArrayBuffer


/**
 * Created by sinc on 31.08.13.
 */
class Network {
	class Member(val name: String) {
		val contacts = new ArrayBuffer[Network#Member]

		def equals(member: Member): Boolean = {
			members.count(_.name == member.name) > 0
		}
	}

	private val members = new ArrayBuffer[Member]

	def join(name: String) = {
		val m = new Member(name)
		members += m
		m
	}
}

object Network extends App{
	val chatter = new Network
	val myFace = new Network

	val fred = chatter.join("Fred")
	val barney = myFace.join("Barney")

	fred.contacts += barney

	val oleg = chatter.join("Oleg")

	fred.equals(oleg)
	fred.equals(barney)
}
