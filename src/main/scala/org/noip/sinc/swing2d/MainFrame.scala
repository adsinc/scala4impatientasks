package org.noip.sinc.swing2d

import scala.swing.{FlowPanel, Frame}

class MainFrame extends Frame {
  contents = new FlowPanel(new BallsComponent)
  resizable = false
  pack()
  override def closeOperation() = System.exit(0)
}
