package de.burnynet.lastfmapi.types

import xml.NodeSeq

/**
 * An authenticated Last.fm API session.
 * This session can be used for authenticated API calls.
 * @author tobi
 */
class Session(val name: String, val sessionKey: String, val subscriber: Boolean) {}

object Session {
  def apply(xml: NodeSeq): Session = {

    /*
      <session>
        <name>MyLastFMUsername</name>
        <key>d580d57f32848f5dcf574d1ce18d78b2</key>
        <subscriber>0</subscriber>
      </session>
    */

    new Session((xml \ "name").text, (xml \ "key").text, (xml \ "subscriber").text equals "1")
  }
}