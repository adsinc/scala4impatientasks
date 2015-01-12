package org.noip.sinc.chapter20.task2

import scala.actors._

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import org.noip.sinc.chapter20.task2.Helper.{invertColors, stopWatch, invertColorsAct}

object Main extends App {

	val img1 = ImageIO.read(new File("big_image.png"))
	stopWatch() {
		invertColorsAct(img1)
	}
	ImageIO.write(img1, "png", new File("out1.png"))

	val img2 = ImageIO.read(new File("big_image.png"))
	stopWatch() {
		invertColors(img2)
	}
	ImageIO.write(img2, "png", new File("out2.png"))
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

	def invertColorsAct(img: BufferedImage): Unit = {
		val saver = new Saver(partNum = 10)
		val step = img.getHeight / 10
		(0 until 10) foreach { p =>
			Invertor(saver).start() ! InvMsg(img, p * step, step)
		}
	}

	case class InvMsg(image: BufferedImage, from: Int, to: Int)

	case class OkMsg(msg: String = "OK")

	case class Invertor(saver: Saver) extends Actor {
		override def act() {
			receive {
				case InvMsg(image, from, to) => {
					invertColors(image.getSubimage(0, from, image.getWidth, to))
					saver ! OkMsg
				}
			}
		}
	}
	case class Saver(partNum: Int) extends Actor {
		var okCnt = 0
		override def act() {
			while(okCnt < partNum) {
				receive {
					case OkMsg => okCnt += 1
				}
			}
		}
	}
}