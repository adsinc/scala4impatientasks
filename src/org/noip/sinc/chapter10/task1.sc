import java.awt.geom.Ellipse2D

trait RectangleLike {

  def translate(dx: Double, dy: Double): Unit = setFrame(getX + dx, getY + dy, getWidth, getHeight)

  def grow(h: Double, w: Double): Unit = setFrame(getX, getY, getWidth + w, getHeight + h)

  def getX: Double
  def getY: Double
  def getWidth: Double
  def getHeight: Double
  def setFrame(x: Double, y: Double, w: Double, h: Double): Unit

  override def toString = "%s %s %s %s".format(getX, getY, getWidth, getHeight)
}
val egg = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
egg.translate(10, -10)
egg.grow(10, 20)
egg