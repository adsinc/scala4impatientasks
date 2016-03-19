trait FieldObjects
object Title extends FieldObjects
object Author extends FieldObjects
class Book {
	var title: String = ""
	var author: String = ""
	private var useNextArgsAs: Any = null
	def set(obj: FieldObjects): this.type = {useNextArgsAs = obj; this}
	def to(arg: String): this.type = {
		useNextArgsAs match {
			case _:Title.type => title = arg
			case _:Author.type => author = arg
		}
		this
	}
	override def toString = s"$title $author"
}
val book = new Book
book set Title to "My book" set Author to "Aleksei Dolgii"
