class Square(x: Int, y: Int, width: Int) extends java.awt.Rectangle(x, y, width, width) {
  def this(width: Int) = this(0, 0, width)
  def this() = this(0)
}
new Square()
new Square(5)
new Square(1, 2, 3)