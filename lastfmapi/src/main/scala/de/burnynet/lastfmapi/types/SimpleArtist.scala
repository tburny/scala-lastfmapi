package de.burnynet.lastfmapi.types

import xml.NodeSeq
import de.burnynet.lastfmapi.XmlHelper.fetchList


class SimpleArtist(
                    val name: String,
                    val url: String,
                    val images: List[Image]) {


  /**
   * Find the image with the specified size
   * @param imageSize
   * @return [[scala.Some]]([[de.burnynet.lastfmapi.types.Image]] or [[scala.None]]
   */
  def imageWithSize(imageSize: ImageSize.ImageSize): Option[Image] = {
    val l = images filter (
      p => p.size equals imageSize
      )
    if (l.size > 0)  Some(l(0)) else None
  }

}

object SimpleArtist {

  def apply(xml: NodeSeq): SimpleArtist = {
    val root = xml
    new SimpleArtist(
      extractName(root),
      extractUrl(root),
      Image.findAllIn(root)
    )
  }

  def extractName(xml: NodeSeq) : String = {
    (xml \ "name").text
  }
  def extractUrl(xml: NodeSeq) : String =
    (xml \ "url").text

  /**
   * Find all artist objects within xml
   * @param xml The xml structure. Only direct children are used for finding
   *            [[de.burnynet.lastfmapi.types.SimpleArtist]]s
   * @return A [[scala.collection.immutable.List]][ [[de.burnynet.lastfmapi.types.SimpleArtist]]]
   */
  def findAllIn(xml: NodeSeq) =
    fetchList(xml \ "artist", {
      xml: NodeSeq => SimpleArtist(xml)
    })
}
