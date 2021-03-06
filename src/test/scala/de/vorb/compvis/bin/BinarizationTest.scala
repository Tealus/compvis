package de.vorb.compvis.bin

import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageOp
import java.awt.image.ColorConvertOp

object BinarizationTest extends App {
  print("Reading image... ");
  val color = ImageIO.read(new File("src/test/resources/color.png"))
  println("done.")

  val gray = new BufferedImage(color.getWidth, color.getHeight,
    BufferedImage.TYPE_BYTE_GRAY)

  // conversion
  val conv: BufferedImageOp =
    new ColorConvertOp(color.getColorModel().getColorSpace(),
      gray.getColorModel().getColorSpace(), null);
  print("Converting image to grayscale... ");
  conv.filter(color, gray);
  println("done.")

  print("Saving image... ")
  ImageIO.write(gray, "png", new File("src/test/resources/gray.png"))
  println("done.")

  val bin = new BufferedImage(color.getWidth, color.getHeight,
    BufferedImage.TYPE_BYTE_BINARY)
  
  print("Image binarization... ")
  val binarization = new Sauvola(gray, bin)
  binarization.binarize
  println("done.")

  print("Saving image... ")
  ImageIO.write(bin, "png",
    new File("src/test/resources/sauvola.png"))
  println("done.")

  println("Exit.")
}