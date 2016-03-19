import java.io._
import scala.collection.mutable.ArrayBuffer
import scala.Serializable
class Person(val name: String, val age: Int) extends Serializable {
  private val friends = new ArrayBuffer[Person]

  def addFriend(friend: Person) = { friends += friend }

  override def toString = "%s %s %s".format(name, age, friends.map(_.name).mkString("{", ", ", "}"))
}
val alex = new Person("alex", 27)
val ivan = new Person("ivan", 28)
val roma = new Person("roma", 26)
alex.addFriend(ivan)
alex.addFriend(roma)
ivan.addFriend(alex)
roma.addFriend(alex)
val persons = Array[Person](alex, roma, ivan)
persons.mkString(", ")
val out = new ObjectOutputStream(new FileOutputStream(new File("objs")))
out.writeObject(persons)
out.close()
val in = new ObjectInputStream(new FileInputStream(new File("objs")))
val savedPersons = in.readObject().asInstanceOf[Array[Person]]
savedPersons.mkString(", ")