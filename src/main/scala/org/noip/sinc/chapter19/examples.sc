import scala.actors.Actor._
import scala.actors.Actor

/**
 * @author dolgiy
 */
//1
class HiActor extends Actor {
	def act() {
		while(true) {
			receive {
				case "Hi" => println("Hello")
			}
		}
	}
}
val actor1 = new HiActor
actor1.start() ! "Hi"

val actor2 = actor {
	while(true) {
		receive {
			case "Hi" => println("Hello")
		}
	}
}

//2
actor1 ! "Hi"
