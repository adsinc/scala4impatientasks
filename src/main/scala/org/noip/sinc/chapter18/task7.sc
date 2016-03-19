object Executor {
	def process(obj: { def close(): Unit },
							 f: {def close(): Unit} => Unit) = {
		f(obj)
	}
}


trait Speaker {
	def sayHello()
	def close(): Unit
}
val hs = new Speaker{
	def sayHello() = println("Hello")
	def close() = println("Closed")
}
val es = new Speaker{
	def sayHello() = new Error
	def close() = println("Closed")
}
def speakCaller(s: {def close(): Unit}) = {
	try { s match {
			case sp: Speaker => sp.sayHello()
			case _ =>
		}
	} finally { s.close() }
}

Executor.process(hs, speakCaller)
Executor.process(es, speakCaller)