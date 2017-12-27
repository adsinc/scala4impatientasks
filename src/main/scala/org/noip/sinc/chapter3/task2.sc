val a = Array(1, 2, 3, 4, 5, 6, 7)

for {
  i <- 0.until(a.length, 2)
  if i < a.length - 1
  x = a(i)
  y = a(i + 1)
} {
  a(i) = y
  a(i + 1) = x
}

a