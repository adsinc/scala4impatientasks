package org.noip.sinc.chapter11

import java.io.File

import org.noip.sinc.chapter11.Tasks.{BitSequence, RichFile}

object Tasks {

  class BitSequence(val data: Long) {
    def apply(i: Int): Boolean = {
      def bitValue(i: Int): Long = (data >> i) ^ 0xfffffffffffffffeL
      bitValue(i) == 1
    }

    def update(i: Int, v: Boolean): BitSequence = {
      def setBit = data | (1L << i)
      def unsetBit = data & ~(1L << i)
      val newData = if(v) setBit else unsetBit
      new BitSequence(newData)
    }

    override def toString: String = data.toBinaryString
  }

  object BitSequence {
    def apply(): BitSequence = new BitSequence(0)
  }

  object RichFile {
    def unapply(arg: File): Option[(String, String, String)] = {
      val path = arg.getAbsolutePath
      val sepIdx = path lastIndexOf File.separator
      if(sepIdx < 0) None
      else {
        val fileFullName = path.substring(sepIdx + 1)
        val extIdx = fileFullName.lastIndexOf('.')
        if(extIdx < 0) None
        else {
          val (name, ext) = fileFullName.splitAt(extIdx)
          Some((path.substring(0, sepIdx), name, ext.substring(1)))
        }
      }
    }
  }
}

object BitSequenceTest extends App {
  val bs = BitSequence()
  val b1 = bs(0) = true
  val b2 = b1(1) = true
  val b3 = b2(63) = true
  println(b3)
}

object UnapplyTest extends App {
  val f = new File("hello")
  val RichFile(dir, file, ext) = f
  println(s"$dir $file $ext")
}