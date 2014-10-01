import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

Future(5) map (2*) map println

Future(5) map (_/2) map println

def f(a: Int) = Future(a * 5)
Future(2) flatMap f map println

