package org.noip.sinc.financy

import javax.swing.UIManager

object FinancyRunner extends App {
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  val frame = new MainFrame
  frame.visible = true
}
