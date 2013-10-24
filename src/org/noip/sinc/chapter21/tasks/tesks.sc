//2
/**
 * Определите оператор +%, добавляющий указанный процент к значению.
 * Например, выражение 120 +% 10 должно вернуть 132.
 * Подсказка: так как операторы являются методами, а не функциями,
 * вам также придется реализовать неявное преобразование.
 */
class PercentOperations(val x: Int) {
	def +%(percent: Int) = x + x * percent / 100
}
implicit def numericToPercentOperations(x: Int):  PercentOperations = new PercentOperations(x)

120 +% 10
//1
//42 -> "Hello"
//"Hello" -> 42

