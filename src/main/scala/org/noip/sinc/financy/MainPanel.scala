package org.noip.sinc.financy

import scala.swing.BorderPanel.Position
import scala.swing.{ScrollPane, Table, BorderPanel}

class MainPanel extends BorderPanel {
  add(createComp, Position.Center)

  def createComp = {
    val table = new Table(
      Array(
        Array[Any](1, 2, 3),
        Array[Any](4, 5, 6),
        Array[Any](8, 9, 10)
      ),
      (1 to 3 map (i => s"col$i")).toArray
    )
    new ScrollPane(table)
  }
}
