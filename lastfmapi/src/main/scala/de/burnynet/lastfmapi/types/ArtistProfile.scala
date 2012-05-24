package de.burnynet.lastfmapi.types

import xml.NodeSeq
import java.util.Date
import de.burnynet.lastfmapi.DateTimeConverter

class Biography (
                 val published: Date,
                 val summary: String,
                 val content: String) {}

object Biography {
  def apply(xml:NodeSeq) : Biography = {
    val published = DateTimeConverter.convertIso8601Date(xml \ "date" text)
    val summary = xml \"summary"  text
    val content = xml \"content"  text

    new Biography(published, summary, content)
  }
}

class ArtistProfile (
                     name: String,
                     val musicBrainzId: String,
                     url: String,
                     images: List[Image],
                     val streamable: Boolean,
                     val listeners: Int,
                     val playcount: Int,
                     val similarArtists: List[SimpleArtist],
                     val tags: List[Tag],
                     val biography: Biography
                     ) extends SimpleArtist(name, url, images) {


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

 // override def toString() = "name=" + name + ",listeners=" + listeners + ",musicBrainzId=" + musicBrainzId + "images="
}


object ArtistProfile {

  def apply(xml: NodeSeq): ArtistProfile = {
    val root = xml

    val name = SimpleArtist.extractName(root)
    val url = SimpleArtist.extractUrl(root)
    val musicBrainzId = root \ "mbid" text
    val images: List[Image] = Image.findAllIn(root)
    val streamable = (root \ "streamable" text) equals ("1")
    val listeners = (root \ "stats" \ "listeners").text.toInt
    val playcount = (root \ "stats" \ "playcount").text.toInt

    new ArtistProfile(
      name,
      musicBrainzId,
      url,
      images,
      streamable,
      listeners,
      playcount,
      SimpleArtist.findAllIn(xml \ "similar"),
      Tag.findAllIn(xml \ "tags"),
      Biography(xml \ "bio"))
  }

}

