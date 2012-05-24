package de.burnynet.lastfmapi.types

import xml.NodeSeq
import de.burnynet.lastfmapi.XmlHelper.fetchList


class SimpleArtist(
                    val name: String,
                    val url: String,
                    val images: List[Image]) {}

object SimpleArtist {

  def apply(xml: NodeSeq): SimpleArtist = {
    new SimpleArtist(
      extractName(xml),
      extractUrl(xml),
      Image.findAllIn(xml)
    )
  }

  def extractName(xml: NodeSeq) =
    xml \ "name" text

  def extractUrl(xml: NodeSeq) =
    xml \ "url" text

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
