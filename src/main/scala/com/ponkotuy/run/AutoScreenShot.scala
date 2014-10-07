package com.ponkotuy.run

import java.awt.Robot
import java.io.File
import javax.imageio.ImageIO

import com.ponkotuy.awt.Implicits._
import com.ponkotuy.jna.WindowList

import scala.collection.JavaConverters._

/**
 *
 * Date 14/09/30.
 */
object AutoScreenShot extends App {
  val ProgramName = "Hoge"
  val Stopper = ImageIO.read(new File("stopper.bmp"))
  val robot = new Robot()
  val dir = "tmp/"

  new File(dir).mkdir()

  WindowList.get().asScala.find(_.title contains ProgramName).map { target =>
    robot.setAutoDelay(50)
    target.active()
    while(target.screenShot().find(Stopper).isEmpty) {
      target.saveScreenShot("tmp/")
      robot.click(target.rect.center)
      robot.delay(800)
    }
  }.getOrElse(println(ProgramName + " Not Found"))
}
