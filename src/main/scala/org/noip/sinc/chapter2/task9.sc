def product(s: String): Long =
	if(s.length == 1) s.head.toLong else s.head * product(s.tail)
product("1")
product("H")
product("He")
product("Hel")
product("Hell")
product("Hello")