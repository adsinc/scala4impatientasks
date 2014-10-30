package org.noip.sinc.swing2d

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.Timer

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

  def apply(e: Event) { println("tick")}

  preferredSize = new Dimension(800, 600)
  val rand = new Random
  lazy val balls = createBalls(10)
  listenTo(new Controller)

  def createBalls(n: Int): Seq[Ball] = 0 until n map {_ =>
    new Ball(10, rand.nextInt(size.width), rand.nextInt(size.height))
  }

  override protected def paintComponent(g: Graphics2D) = {
    balls foreach (_ draw g)
  }
}

class Controller extends Publisher {
  new Timer(100, new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      publish(null)
    }
  }).start()
}

object Context extends MainContext {
  val g: Double = 9.8
}