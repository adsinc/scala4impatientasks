import scala.collection.mutable

val map = mutable.LinkedHashMap(
	"Tuesday" -> java.util.Calendar.TUESDAY,
	"Monday" -> java.util.Calendar.MONDAY,
	"Wednesday" -> java.util.Calendar.WEDNESDAY,
	"Thursday" -> java.util.Calendar.THURSDAY,
	"Friday" -> java.util.Calendar.FRIDAY,
	"Saturday" -> java.util.Calendar.SATURDAY,
	"Sunday" -> java.util.Calendar.SUNDAY
)
for((k, v) <- map) println(k)

