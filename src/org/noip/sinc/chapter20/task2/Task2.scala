package org.noip.sinc.chapter20.task2

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import org.noip.sinc.chapter20.task2.Helper.{invertColors, stopWatch}

object Main extends App {

	val img = ImageIO.read(new File("big_image.png"))

	stopWatch() {
		invertColors(img)
	}
	val f = new File("out.png")
	ImageIO.write(img, "png", f)


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