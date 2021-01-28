package com.ponkotuy.run

import java.awt.Robot
import java.io.File
import javax.imageio.ImageIO

import com.ponkotuy.awt.Implicits._
import com.ponkotuy.jna.WindowList

import scala.jdk.CollectionConverters._

object AutoScreenShot extends App {
  val ProgramName = "まいてつ"
  val Stopper = ImageIO.read(new File("stopper.bmp"))
  val robot = new Robot()
  val dir = if(args.nonEmpty) s"${args(0)}/" else "tmp/"
  var count = 0

  new File(dir).mkdir()

  WindowList.get().asScala.find(_.title contains ProgramName).map { target =>
    robot.setAutoDelay(50)
    target.active()
    while(target.screenShot().find(Stopper).isEmpty) {
      count += 1
      if(count % 10 == 0) {
        target.saveScreenShot(dir)
        robot.click(target.rect.center)
      }
      robot.delay(30)
    }
  }.getOrElse(println(ProgramName + " Not Found"))
}
