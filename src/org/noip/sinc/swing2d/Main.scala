package org.noip.sinc.swing2d

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.Timer

import org.noip.sinc.chapter7.random
import org.noip.sinc.swing2d.Context

import scala.swing.Reactions.Reaction
import scala.swing._
import scala.swing.event.Event
import scala.util.Random

object Main extends App {
  val mf = new MainFrame
  mf.visible = true
}

class MainFrame extends Frame {
  contents = new FlowPanel(new BallsComponent)
  resizable = false
  pack()
  override def closeOperation() = System.exit(0)
}

case class Ball(r: Int, x: Int, y: Int) {
  def draw(g: Graphics2D) = g.fillOval(x, y, r, r)
}

class BallsComponent extends Component {
  preferredSize = new Dimension(800, 600)
	var balls = Seq[Ball]()

	listenTo(new Controller(createBalls(10)))
	reactions += {
		case be: BallEvent => balls = be.balls; repaint()
	}

  def createBalls(n: Int): Seq[Ball] = {
		if(size.width <= 0 || size.height <= 0) Seq()
		else 0 until n map { _ =>
			new Ball(10, Context.random.nextInt(size.width), Context.random.nextInt(size.height))
		}
	}

  override protected def paintComponent(g: Graphics2D) = {
    balls foreach (_ draw g)
  }
}

object Context extends MainContext {
  val g: Double = 9.8
	val tick: Int = 50
	val random = new Random
}