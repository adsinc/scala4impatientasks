package org.noip.sinc.chapter20.task2

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import org.noip.sinc.chapter20.task2.Helper.{invertColors, stopWatch}

object Main extends App {

//	val img = ImageIO.read(new File("big_image.jpg"))
	val img = ImageIO.read(new URL("http://cstrips.bitstrips.com/XDSDS_925V.png"))

	stopWatch() {
		invertColors(img)
	}

	ImageIO.write(img, "jpg", new File("out.jpg"))
}

object Helper {

	def stopWatch()(f: => Unit) = {
		val start = System.currentTimeMillis()
		f
		val time = System.currentTimeMillis() - start
		println(s"Executed in $time ms")
	}

	def invertColors(img: BufferedImage): Unit = {
		for {
			x <- 0 until img.getWidth
			y <- 0 until img.getHeight
		} img.setRGB(x, y, ~img.getRGB(x, y))
	}

}