package com.ponkotuy.jna

import java.awt.{Point, Robot}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import com.sun.jna.Native
import com.ponkotuy.awt.Implicits._

/**
 * Date: 2014/10/04.
 */
class Window(user: User32, hWnd: Int) {
  val robot = new Robot

  private[this] var ssNo = 0

  lazy val rect: Rect = {
    val rect = new Rect
    User32.instance.GetClientRect(hWnd, rect)
    User32.instance.ClientToScreen(hWnd, rect)
    rect
  }

  lazy val title: String = {
    val buffer: Array[Byte] = new Array[Byte](1024)
    User32.instance.GetWindowTextA(hWnd, buffer, buffer.length)
    val title = Native.toString(buffer, "MS932")
    title
  }

  @deprecated def focus(): Unit = user.SetFocus(hWnd)

  def active(): Unit = robot.click(new Point(rect.left + rect.right/2, rect.top - 5))

  def screenShot(): BufferedImage = robot.createScreenCapture(rect.toAwt)

  def saveScreenShot(fNameHead: String = ""): Unit = {
    ImageIO.write(screenShot(), "png", new File(s"$fNameHead$ssNo.png"))
    ssNo += 1
  }
}
