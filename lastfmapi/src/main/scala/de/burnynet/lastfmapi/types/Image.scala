package de.burnynet.lastfmapi.types

import xml.NodeSeq
import de.burnynet.lastfmapi.XmlHelper._

/**
 * Represents an image
 * @param url
 * @param size
 */
case class Image(url: String, size: ImageSize.ImageSize) {}

object Image {
  /**
   * Creates a new Image object from an xml node
   * @param i The &lt;image&gt; node
   */
  def apply(i:NodeSeq) : Image = {
    new Image(i text, ImageSize withName (i \ "@size" text))
  }

  def findAllIn(i:NodeSeq) =
    fetchList(i \ "image", {xml:NodeSeq => Image(xml)})
}

/**
 * [[scala.Enumeration]] object for the size of an image
 */
object ImageSize extends Enumeration {
  type ImageSize = Value
  val SMALL = Value("small")
  val MEDIUM = Value("medium")
  val LARGE = Value("large")
  val EXTRA_LARGE = Value("extralarge")
  val MEGA = Value("mega")
}
