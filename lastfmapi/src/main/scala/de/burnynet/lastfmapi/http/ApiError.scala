package de.burnynet.lastfmapi.http

import xml.NodeSeq

class ApiError(xml: NodeSeq) {
  val code: Int = ((xml \\ "error" \ "@code") text).toInt
  val message: String = (xml \\ "error" text)
}

object ApiError {
  def apply(xml: NodeSeq): Option[ApiError] = {
    (xml \\ "error").text match {
      case "" => None
      case _ => Some(new ApiError(xml))
    }

  }
}
