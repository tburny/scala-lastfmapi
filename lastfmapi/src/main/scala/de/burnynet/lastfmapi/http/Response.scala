package de.burnynet.lastfmapi.http

import xml.{Elem, NodeSeq}

/**
 * Wrapper around the response Xml. Takes care of the last.fm result, e.g. if there was an error or not and if yes,
 * what the reason has been.
 * @author tobi
 */

class Response(val xml: NodeSeq) {

  /**
   * Gets the root element of the xml inside the &lt;lfm&gt; tag
   */
  lazy val dataXml = xml(0).child.collect {case e : Elem => e}

  /**
   * Converts this response into a result object
   * @param xmlConverter The function which creates the result object from the respones xml
   * @tparam T The type of the result object
   * @return Either a [[scala.Left]]([[de.burnynet.lastfmapi.http.ApiError]], if the request produced an API error or a
   *         [[scala.Right]](T) if the request was successful
   */
  def result[T]( xmlConverter :(NodeSeq) => T) : Either[ApiError, T] =
    // It is important for the construction of the objects to use dataXml here
    // The data elements get the root node which is the topmost node being part of the objects data
    // See also https://github.com/tburny/scala-lastfmapi/wiki/Guidelines
    error.toLeft(xmlConverter(dataXml))

  /**
   * Return the ApiError or None
   */
  val error: Option[ApiError] = ApiError(xml)

}

object Response {
  def apply(xml: NodeSeq) = new Response(xml)
}





