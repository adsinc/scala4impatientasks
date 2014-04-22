import scala.collection.mutable.ArrayBuffer

abstract class Item {
	def price: Double
	def description: String
}
class SimpleItem(val price: Double, val description: String) extends Item
class Bundle extends Item{
	private val elements: ArrayBuffer[Item] = ArrayBuffer[Item]()
	override def description: String = elements.map(_.description).mkString("{", ", ", "}")
	override def price: Double = elements.map(_.price).sum
	def +(item: Item) = { elements += item; this }
}
val cat = new SimpleItem(1000, "cat")
val dog = new SimpleItem(3000, "dog")
val ipad = new SimpleItem(18000, "ipad")
val bundle = new Bundle
bundle + cat + dog + ipad
bundle.description
bundle.price