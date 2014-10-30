package org.noip.sinc.swing2d

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.Timer

import scala.swing.Publisher
import scala.swing.event.Event

class Controller(balls: => Seq[Ball]) extends Publisher {
	new Timer(Context.tick, new ActionListener {
		def actionPerformed(e: ActionEvent) = {
			publish(BallEvent(balls))
		}
	}).start()
}

case class BallEvent(balls: Seq[Ball]) extends Event