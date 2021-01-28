package com.ponkotuy.awt

import java.awt.event.InputEvent
import java.awt.image.BufferedImage
import java.awt.{Color, Point, Robot}

object Implicits {
  val DiffThres = 50

  implicit class BufferedImageWrapper(buf: BufferedImage) {
    lazy val array: Array[Int] =
      buf.getData.getPixels(0, 0, width, height, new Array[Int](width * height * 3))

    def width: Int = buf.getWidth

    def height: Int = buf.getHeight

    def equal(other: BufferedImage): Boolean = {
      buf.getWidth == other.width && buf.height == other.height && {
        buf.array sameElements other.array
      }
    }

    def find(other: BufferedImage): List[Point] = {
      val builder = List.newBuilder[Point]
      val otherBaseRGB = other.getRGB(0, 0)
      for {
        x <- 0 to (this.width - other.width)
        y <- 0 to (this.height - other.height)
        if buf.getRGB(x, y) == otherBaseRGB
      } {
        var diff = 0
        var i = 0
        var j = 0
        while (j != other.height && diff < DiffThres) {
          diff += (if(buf.getRGB(x + i, y + j) != other.getRGB(i, j)) 1 else 0)
          i += 1
          if(i == other.width) { j += 1; i = 0 }
        }
        if(j == other.height) builder += new Point(x, y)
      }
      builder.result()
    }
  }

  def diffColor(left: Int, right: Int): Int = {
    val bit = left ^ right
    getRed(bit) + getGreen(bit) + getBlue(bit)
  }

  def getRed(color: Int): Int = (color >> 16) & 0xFF
  def getGreen(color: Int): Int = (color >> 8) & 0xFF
  def getBlue(color: Int): Int = (color >> 0) & 0xFF

  implicit class RobotWrapper(robot: Robot) {
    val Enter: Byte = 0x4E
    def click(): Unit = {
      robot.mousePress(InputEvent.BUTTON1_MASK)
      robot.mouseRelease(InputEvent.BUTTON1_MASK)
    }

    def click(point: Point): Unit = {
      robot.mouseMove(point.x, point.y)
      click()
    }
  }
}
