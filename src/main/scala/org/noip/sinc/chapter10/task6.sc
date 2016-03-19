import java.io.{FileInputStream, InputStream, BufferedInputStream}

import java.io.BufferedInputStream

trait Buffered extends InputStream {
  val instance = new BufferedInputStream(this)
  override def read(): Int = { instance.read() }
}

val is = new FileInputStream("/usr/share/dict/words")
val buf = new FileInputStream("/usr/share/dict/words") with Buffered

var start = 0L

start = System.currentTimeMillis()
for(i <- 0 to 10000) buf.read()
System.currentTimeMillis - start

start = System.currentTimeMillis()
for(i <- 0 to 10000) is.read()
System.currentTimeMillis - start