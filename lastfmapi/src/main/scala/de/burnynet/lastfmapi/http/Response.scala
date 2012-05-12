package de.burnynet.lastfmapi.http

import xml.NodeSeq


/**
 *
 * @author tobi
 */

class Response(val xml: NodeSeq) {

  lazy val status = (xml \ "@status").text.toLowerCase match {
    case "ok" => ResponseStatus.OK
    case _ => ResponseStatus.FAILED
  }

  /**
   * Return the Error or None
   */
  lazy val error: Option[Error] = Error(xml)

}

object Response {
  def apply(xml: NodeSeq) = new Response(xml)
}

class Error(xml: NodeSeq) {
  val code: Int = ((xml \\ "error" \ "@code") text).toInt
  val message: String = (xml \\ "error" text)
}

object Error {
  def apply(xml: NodeSeq): Option[Error] = {
    (xml \\ "error").text match {
      case "" => None
      case _ => Some(new Error(xml))
    }

  }
}

object ResponseStatus extends Enumeration {
  type ResponseStatus = Value
  val OK, FAILED = Value
}