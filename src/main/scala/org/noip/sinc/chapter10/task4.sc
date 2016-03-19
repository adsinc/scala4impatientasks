trait Logger {
  def log(msg: String): String
}
trait CryptoLogger extends Logger {
  val key = 3
  def log(msg: String): String = msg map crypt
  def crypt(ch: Char) = (ch.toInt + key).toChar
  def decrypt(ch: Char) = (ch.toInt - key).toChar
  def decryptLog(str: String) = str map decrypt
}

class LogClass3 extends CryptoLogger
val c = new LogClass3
c log "hello"

class LogClassM3 extends CryptoLogger {
  override val key: Int = -3
}
val c1 = new LogClassM3
c1 decryptLog (c1 log "hello")
