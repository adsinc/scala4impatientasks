def unless(x: Boolean)(f: => Unit) = {
  if(!x) f else ()
}

for(i <- 0 to 9) {
  unless(i == 5) {
    print(i)
  }
}

