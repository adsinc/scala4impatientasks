sealed abstract class Item
case class Article(description: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, items: Item*) extends Item

def price(it: Item): Double = it match {
  case Article(_, p) => p
  case Bundle(_, disc, items @ _*) => (items map price).sum - disc
}

val b = Bundle("Fathers day special", 20.0,
  Article("Book1", 39.95),
  Bundle("Games", 10.0,
    Article("BattleField 4", 12.0),
    Article("Skyrim", 30)))

price(b)