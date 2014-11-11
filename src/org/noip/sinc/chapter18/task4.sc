import scala.collection.mutable.ArrayBuffer

class Network {
  class Member(name: String) {
    val contacts = new ArrayBuffer[Network#Member]()
    override def equals(obj: scala.Any) = obj match {
      case null => false
      case a:Member => a == this
      case _ => false
    }
  }

  private val members = new ArrayBuffer[Member]()

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}
val chatter = new Network
val myFace = new Network
val fred = chatter.join("Fred")
val barney = myFace.join("Barney")
val mike = myFace.join("Mike")
fred.contacts += barney
fred equals barney
barney equals mike