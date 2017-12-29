import java.util.TimeZone

val prefix = "America/"
TimeZone.getAvailableIDs
  .filter(_ startsWith prefix)
  .map(_ drop prefix.length)
  .sorted