package org.noip.sinc.chapter8.task4

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 15:36
 */
abstract class Item {
	def price: Double

	def description: String
}

class SimpleItem(priceArg: Double, descriptionArg: String) extends Item {
	val price = priceArg
	val description = descriptionArg
}

class Bundle extends Item {
	private val items = new ArrayBuffer[Item]()

	def price: Double = items.map(_.price).sum

	def description: String = items.map(_.description).mkString("Items: [", ", ", "]")

	def addItem(item: Item) {
		items += item
		println("Item " + item.description + " was added")
	}
}

object BundleTest extends App {
	val items = Array(
		new SimpleItem(100, "Утюг")
		, new SimpleItem(200, "Мясорубка")
		, new SimpleItem(300, "Планшет")
		, new SimpleItem(400, "Телефон")
	)
	println(items.mkString("\n"))
	val bundle = new Bundle
	for (item <- items) {
		bundle.addItem(item)
		println("Вещи [" + bundle.description + "] стоят " + bundle.price)
	}
}
