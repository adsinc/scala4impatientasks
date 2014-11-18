object Executor {
	def process(obj: { def close(): Unit },
							 f: {def close(): Unit} => Unit) = {
		f(obj)
	}
}

class Data {
	def close(): Unit = ???
}

