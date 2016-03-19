abstract class Shape {
  def centerPoint: Pair[Double, Double]
}

class Circle(val x: Double, val y: Double, val r: Double) extends Shape {
  def centerPoint = (x, y)
}

class Rectangle(val x: Double, val y: Double, val width: Double, val heigth: Double) extends Shape {
  def centerPoint = (x + width / 2, y + heigth / 2)
}

val circle = new Circle(0, 0, 2)
circle.centerPoint
val rectangle = new Rectangle(0, 0, 2, 3)
rectangle.centerPoint
