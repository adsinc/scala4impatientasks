class BitSequence {
  var seq = 0L

  def apply(idx: Int) =
    if( (seq & (1L << idx)) == 0) 0 else 1

  def update(idx: Int, newVal: Int) =
    seq = seq | (1L << idx)

  override def toString: String = seq.toBinaryString
}

val bs = new BitSequence
bs(4) = 1
bs(0) = 1
bs(1) = 1
bs(5) = 1
bs