package com.horstmann.impatient {
object Obj {
	def say() {
		println("Hello")
	}
}
}

package com {
package horstmann {
package impatient {
object Obj {
	def say() {
		println("Hello")
		Hello.say()
	}
}
}
}
}

package com.horstmann {
object Hello {
	def say() { println("Hello") }
}
}