import java.time.LocalDate

implicit class DateInterpolator(sc: StringContext) {
  def date(args: Int*): LocalDate = LocalDate.of(args(2), args(1), args(0))
}

date"${14}-${12}-${2017}"