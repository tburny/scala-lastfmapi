package de.burnynet.lastfmapi.types

import xml.NodeSeq
import de.burnynet.lastfmapi.XmlHelper._

/**
 * A last.fm tag
 * @author tobi
 */
class Tag(
           val name:String,
           val url:String)

object Tag {

  def apply(xml:NodeSeq) =
   new Tag (xml \ "name" text, xml \ "url" text)


  def findAllIn(i:NodeSeq) =
    fetchList(i \ "tag", {xml:NodeSeq => Tag(xml)})
}