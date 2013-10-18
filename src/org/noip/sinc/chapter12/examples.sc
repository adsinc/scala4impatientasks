import java.awt.event
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton
import scala.math._

val num = 3.14
val fun = ceil _
fun(num)
Array(3.14, 2.11, 5.2).map(fun)
Array(3.14, 2.11, 5.2).map((x: Double) => x * 3)
def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
valueAtOneQuarter(ceil _)
valueAtOneQuarter(sqrt _)
def mulBy(factor: Double) = (x : Double) => factor * x
val quintuple = mulBy(5)
quintuple(20)
(1 to 9).map(0.1 * _)
(1 to 9).map("*" * _).foreach(println _)
(1 to 9).filter(_ % 2 == 0)
(1 to 9).reduceLeft(_ * _)
"Marry had a little lamb".split(" ").sortWith(_.length < _.length)
//12.6
val triple = mulBy(3)
val half = mulBy(0.5)
println(triple(14) + " " + half(14))
//12,7
implicit def makeAction(action: (ActionEvent) => Unit) = new ActionListener {
	def actionPerformed(event: ActionEvent) { action(event) }
}
var counter = 0
val button = new JButton("Hello")
button.addActionListener((event: ActionEvent) => counter += 1)
//12.8
def mul(x: Int, y: Int) = x * y
def mulOneAtATime(x: Int)(y: Int) = x * y
mulOneAtATime(6)(7)