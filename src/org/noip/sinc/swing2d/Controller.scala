package org.noip.sinc.swing2d

import java.awt.Dimension
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.Timer

import scala.swing.Publisher
import scala.swing.event.Event

class Controller(balls: => Seq[Ball], size: => Dimension) extends Publisher {
	var bs: Seq[Ball] = null
	new Timer(Context.tick, new ActionListener {
		def actionPerformed(e: ActionEvent) = {
			if(bs == null || bs.length == 0) bs = balls
			bs = moveBalls(bs)
			publish(BallEvent(bs))
		}
	}).start()

	def moveBalls(balls: Seq[Ball]) = balls map {
			b => {
				val sign = if (b.y > size.height) -1 else 1
				val v = b.velocity + dv * sign
				b copy (velocity = v * sign, y = (b.y + v * sign).toInt)
			}
		}

	val dv = Context.g * Context.tick / 1000
}

case class BallEvent(balls: Seq[Ball]) extends Event