package org.noip.sinc.swing2d

import scala.swing._
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
  val rand = new Random
  lazy val balls = createBalls(10)

  def createBalls(n: Int): Seq[Ball] = 0 until n map {_ =>
    new Ball(10, rand.nextInt(size.width), rand.nextInt(size.height))
  }

  override protected def paintComponent(g: Graphics2D) = {
    balls foreach (_ draw g)
  }
}