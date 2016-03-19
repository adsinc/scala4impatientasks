object Suite extends Enumeration {
	type Suite = Value
	val HEARTS = Value("\u2665")
	val DIAMONDS = Value("\u2666")
	val CLUBS = Value("\u2663")
	val SPADES = Value("\u2660")
}
def isRed(card: Suite.Value) = card == Suite.HEARTS || card == Suite.DIAMONDS
isRed(Suite.HEARTS)
isRed(Suite.DIAMONDS)
isRed(Suite.CLUBS)
isRed(Suite.SPADES)