def values(fun: Int => Int, low: Int, high: Int) =
  low to high map {v => (v, fun(v))}

values(x => x * x, -5, 5)