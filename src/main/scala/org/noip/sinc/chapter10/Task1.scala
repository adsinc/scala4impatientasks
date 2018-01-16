package org.noip.sinc.chapter10

import java.awt.geom.Ellipse2D

object Task1 {

  trait RectangleLike {
    def getX: Double
    def getY: Double
    def getWidth: Double
    def getHeight: Double
    def setFrame(x: Double, y: Double, w: Double, h: Double): Unit

    def translate(dx: Double, dy: Double): Unit = {
      setFrame(getX + dx, getY + dy, getWidth, getHeight)
    }

    def grow(h: Int, w: Int): Unit = {
      setFrame(getX, getY, h, w)
    }
  }

  val el = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike[Double]
  el.grow(10, 10)
  el.translate(10, 10)
}
