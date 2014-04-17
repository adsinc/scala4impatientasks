package my.com {
	object Fox {
		def say() { println("auuuu auuuu") }
	}
}

package my {
	object Animal {
		def say() { com.Fox.say() }
	}
}
