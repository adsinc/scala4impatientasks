package org.noip.sinc

import math._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution {

	def toDouble(s: String) = s replaceAll (",", ".") toDouble

	def main(args: Array[String]) {
		val lon = toDouble(readLine)
		val lat = toDouble(readLine)
		val n = readInt

		def parseCoordStr(coordStr: String) = {
			val spl = coordStr split ";"
			toDouble(spl(0)) -> toDouble(spl(1))
		}

		def dist(clon: Double, clat: Double) = {
			val x = (clon - lon) * cos((clat + lat) * 0.5)
			val y = clat - lat
			sqrt(x * x + y * y) * 6371
		}

		val defName = (for(i <- 0 until n) yield {
			val Array(_, name, _, _, lon, lat) = readLine split ";"
			name -> dist(toDouble(lon), toDouble(lat))
		}) minBy (_._2) _1

		println(defName)
	}
}