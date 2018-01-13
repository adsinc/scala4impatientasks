package org.noip.sinc.chapter6

object Card extends Enumeration {
  type Card = Card.Value
  //  spades-пики, clubs-трефы,hearts-черви,diamonds-бубны
  val Spades = Value("\u2660")
  val Clubs = Value("\u2663")
  val Hearts = Value("\u2665")
  val Diamonds = Value("\u2666")
}
