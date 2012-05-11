package de.burnynet.lastfmapi.http

import xml.NodeSeq


/**
 *
 * @author tobi
 */

class Response(val xml:NodeSeq) {

  lazy val status = (xml \ "@status").text match {
    case "ok" => ResponseStatus.Ok
    case _ => ResponseStatus.Failed
  }

  /**
   * Return the Error or None
   */
  lazy val error : Option[Error] = Error(xml)

}

object Response {
  def apply(xml:NodeSeq) = new Response(xml)
}

class Error(xml:NodeSeq) {
  val code = Integer.valueOf((xml \\ "error" \ "@code") text)
  val message = (xml \\ "error" text)
}

object Error {
  def apply(xml:NodeSeq) : Option[Error] = {
    (xml \\ "error").text match {
      case "" => None
      case _ => Some(new Error(xml))
    }

  }
}

object ResponseStatus extends Enumeration{
  type ResponseStatus = Value
  val Ok, Failed = Value
}