val strs = Array("a", "aa", "aaa", "aaaa")
val lens = Array(1, 2, 3, 4)

strs.corresponds(lens)(_.length == _)