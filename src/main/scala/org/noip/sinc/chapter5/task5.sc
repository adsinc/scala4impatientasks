import scala.beans.BeanProperty
class Student(@BeanProperty val name: String, @BeanProperty val id: Long)
val a = new Student("Alex", 1)
a.name
a.id
a.getName
a.getId