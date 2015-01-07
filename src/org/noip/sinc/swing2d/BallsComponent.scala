package org.noip.sinc.swing2d

import java.awt.Dimension

import scala.swing._

class BallsComponent extends Component {
  preferredSize = new Dimension(800, 600)
  var balls = Seq[Ball]()

  listenTo(new Controller(createBalls(Context.ballCount), size))

  reactions += {
    case be: BallEvent => balls = be.balls; repaint()
  }

  //todo move to another class
  def createBalls(n: Int): Seq[Ball] = {
    if(size.width <= 0 || size.height <= 0) Seq[Ball]()
    else 0 until n map { _ =>
//      new Ball(10, Context.random.nextInt(size.width), Context.random.nextInt(size.height))
      new Ball(10, Context.random.nextInt(size.width), 0)
    }
  }

  override protected def paintComponent(g: Graphics2D) = {
    balls foreach (_ draw g)
  }
}

case class Ball(r: Int, x: Int, y: Int, velocity: Double = 0) {
  def draw(g: Graphics2D) = g.fillOval(x, y, r, r)
}