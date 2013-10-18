package org.noip.sinc.chapter20.task2

import java.io.File
import scala.actors.Actor
import java.awt.image.BufferedImage

/**
 * @author dolgiy
 */

case class Data(img: BufferedImage, from: Int, to: Int)

object ImageInvertorRunner extends App {
	/**
	 * Инвертирует цвета.
	 * @param img картинка
	 */
	def invertColors(img: BufferedImage) {
		invertColors(img, 0, img.getHeight)
	}
	/**
	 * Инвертирует цвета.
	 * @param img картинка
	 */
	def invertColors(img: BufferedImage, from: Int, to: Int) {
//		println(from + " to " + to)
		for(x <- 0 until img.getWidth; y <- from until to) img.setRGB(x, y, ~img.getRGB(x, y))
	}

	/**
	 * Замеряет скорость выполнения функции fn над данными data.
	 * @param data данные
	 * @param fn функция
	 * @tparam T тип данных
	 * @tparam V тип возвращаемых функцией значений
	 */
	def profile[T, V](data: T, fn: (T) => V) {
		val start = System.currentTimeMillis()
		fn(data)
		val time = System.currentTimeMillis() - start
		println("time=%d ms".format(time))
	}

	var img = javax.imageio.ImageIO.read(new File("src/org/noip/sinc/chapter20/task2/снимок6.png"))
	// один поток
	profile(img, invertColors)
	javax.imageio.ImageIO.write(img, "gif", new File("src/org/noip/sinc/chapter20/task2/снимок6_inverted.png"))

	// на акторах
	class ColorInvertor() extends Actor{
		def act() {
			receive {
				case Data(image, from, to) => invertColors(image, from, to); sender ! "OK"
			}
		}
	}

	val parts = 2

	img = javax.imageio.ImageIO.read(new File("src/org/noip/sinc/chapter20/task2/снимок6.png"))
	val start = System.currentTimeMillis()
	//******************************************
	val futures = for(i <- 0.until(img.getHeight, img.getHeight / parts)) yield new ColorInvertor().start() !! Data(img, i, i + img.getHeight / parts)
	for(future <- futures) future()
	//******************************************
	val time = System.currentTimeMillis() - start
	println("time=%d ms".format(time))
	javax.imageio.ImageIO.write(img, "gif", new File("src/org/noip/sinc/chapter20/task2/снимок6_inverted_actors.png"))

}

