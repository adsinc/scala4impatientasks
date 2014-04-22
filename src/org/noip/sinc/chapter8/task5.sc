class Point(val x: Double, val y: Double)
class LabeledPoint(val label: String, x: Double, y: Double) extends Point(x, y)
val p = new LabeledPoint("Black thursday", 1929, 230.07)
p.label
p.x
p.y