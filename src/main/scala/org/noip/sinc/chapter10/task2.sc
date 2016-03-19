import java.awt.Point
import scala.math.Ordered

class OrderedPoint(x: Int, y: Int) extends Point(x, y) with Ordered[Point] {
  override def compare(that: Point): Int =
    if(this.x <= that.x) that.y - this.y
    else -1
}

val p1 = new OrderedPoint(1, 0)
val p2 = new OrderedPoint(1, 0)
val p3 = new OrderedPoint(1, 1)
p1 compare p2
p1 compare p3
p3 compare p1