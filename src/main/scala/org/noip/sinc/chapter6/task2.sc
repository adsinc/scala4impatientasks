abstract class UnitConversion {
	def convert(v: Double): Double = v
}
object InchesToCentimeters extends UnitConversion {
	override def convert(v: Double): Double = super.convert(v) * 2.54
}
object GallonsToLiters extends UnitConversion {
	override def convert(v: Double): Double = super.convert(v) * 3.78541178
}
object MilesToKilometers extends UnitConversion {
	override def convert(v: Double): Double = super.convert(v) * 1.609344
}
InchesToCentimeters.convert(2)
GallonsToLiters.convert(2)
MilesToKilometers.convert(2)