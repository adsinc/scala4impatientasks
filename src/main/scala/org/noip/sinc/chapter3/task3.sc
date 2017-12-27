val as = Array(1, 2, 3, 4, 5, 6, 7)

for {
  i <- as.indices
  k = if(i == as.length - 1) i
  else if(i % 2 == 0) i + 1
  else i - 1
} yield as(k)