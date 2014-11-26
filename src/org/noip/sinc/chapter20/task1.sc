import scala.actors.Actor._

case class Msg(text: String)

val act = actor {
	while(true) {
		receive {
			case Msg(t) => println(t)
		}
	}
}

act.start()

act ! Msg("Hello")
act ! Msg("World")
