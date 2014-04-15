object Suite extends Enumeration {
	type Suite = Value
	val HEARTS = Value("\u2665")
	val DIAMONDS = Value("\u2666")
	val CLUBS = Value("\u2663")
	val SPADES = Value("\u2660")
}
Suite.HEARTS.toString
Suite.DIAMONDS.toString
Suite.CLUBS.toString
Suite.SPADES.toString