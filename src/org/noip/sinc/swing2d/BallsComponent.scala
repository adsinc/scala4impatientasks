package org.noip.sinc.swing2d

import java.awt.Dimension

import org.noip.sinc.swing2d.Vector2D.Zero

import scala.swing._

class BallsComponent extends Component {
  preferredSize = new Dimension(100, 200)
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
      new Ball(10, Point2D(Context.random.nextInt(size.width), 0))
    }
  }

  override protected def paintComponent(g: Graphics2D) = {
    balls foreach (_ draw g)
  }
}

case class Ball(r: Int, center: Point2D, velocity: Vector2D = Zero) {
  def draw(g: Graphics2D) = g.fillOval(center.x.toInt, center.y.toInt, r, r)
}