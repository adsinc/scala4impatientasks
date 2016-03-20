package org.noip.sinc.bro

class Table(rows: Seq[Seq[Any]]) {
  def |(elem: Any): Table = {
    def lastRow = (rows takeRight 1).flatten
    Table((rows dropRight 1) :+ (lastRow :+ elem))
  }

  def ||(elem: Any): Table = Table(rows :+ Seq(elem))

  override def toString: String = rows map (_ mkString " | ") mkString "\n"
}

object Table {
  def apply(): Table = new Table(Seq.empty[Seq[_]])
  def apply(rows: Seq[Seq[Any]]): Table = new Table(rows)
  def T = Table()
}
