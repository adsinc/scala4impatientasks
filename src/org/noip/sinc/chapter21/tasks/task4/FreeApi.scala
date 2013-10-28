package org.noip.sinc.chapter21.tasks.task4

import java.util.Scanner

/**
 * Некоторые программисты обожают «свободные АР1», которые читаются почти как обычный текст
 * на английском языке.
 * Определите такой API для чтения в консоли целых и вещественных чисел, а также строк, чтобы
 * 'c его помощью можно было записать, например, такую инструкцию:
 * Obtain aString askingFor «Your папе» and anInt askingFor «Your age»
 * and aDouble askingFor «Your weight».
 */

//implicit def askingForToString(af: askingFor) = new Scanner(System.in).next()

object Obtain {
	def aString(implicit s: String): this.type = this
	def anInt(implicit i: Int) = {}
	def aDouble(implicit d: Double) = {}
}

class Ask(val msg: String)

object FreeApi extends App{
	implicit def askToString(ask: Ask): String = new Scanner(System.in).next()
	def askingFor(implicit msg: String) = new Scanner(System.in).next()
	Obtain aString askingFor "Your nапе"// and anInt askingFor "Your age" and aDouble askingFor "Your weight"
}
