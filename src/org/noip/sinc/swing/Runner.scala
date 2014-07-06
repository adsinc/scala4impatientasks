package org.noip.sinc.swing

import scala.swing.{Label, Dimension, MainFrame}

/**
 * @author dolgiy
 */
class UI extends MainFrame {
	title = "GUI Program #1"
	preferredSize = new Dimension(320, 240)
	contents = new Label("Here is the contents!")
}

object Runner extends App {
	val ui = new UI
	ui.visible = true
	println("End of main function")
}
