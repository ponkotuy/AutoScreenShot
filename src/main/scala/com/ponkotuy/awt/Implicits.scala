package com.ponkotuy.awt

import java.awt.event.InputEvent
import java.awt.image.BufferedImage
import java.awt.{Point, Robot}

/**
 * Date: 2014/10/04.
 */
object Implicits {
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
      for {
        x <- 0 to (this.width - other.width)
        y <- 0 to (this.height - other.height)
      } {
        var i = 0
        var j = 0
        while (j != other.height && buf.getRGB(x + i, y + j) == other.getRGB(i, j)) {
          i += 1
          if(i == other.width) { j += 1; i = 0 }
        }
        if(j == other.height) builder += new Point(x, y)
      }
      builder.result()
    }
  }

  implicit class RobotWrapper(robot: Robot) {
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
