import org.noip.sinc.chapter6.Card.Card
import org.noip.sinc.chapter6.Card._
import org.noip.sinc.chapter6.Card

def isRed(card: Card): Boolean =
  card == Diamonds || card == Hearts

Card.values.map(c => s"$c is${if(isRed(c)) "" else " not"} red")