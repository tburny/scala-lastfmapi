package de.burnynet.lastfmapi.http

import collection.mutable.ListBuffer
import dispatch._
import scala.xml._
import de.burnynet.lastfmapi.ApiAccount

/**
 *
 * @author tobi
 */

class HttpApiService(val apiAccount:ApiAccount) extends ApiService {
  val http = new Http

  override def baseUrl() = "http://ws.audioscrobbler.com/2.0"

  def service(call:Call) : Response = {
    val http = new Http
    var req = url(baseUrl())
    var params = call.parameters

    var response: NodeSeq = Text("")

    val method : Request = (call match {
      case AuthenticatedCall => req <<< (params + ("api_sig" -> signCall(call)))
      case NormalCall => req <<? params
    }).gzip

    http(method <> {response = _})
    //returns the parsed xml response as NodeSeq
    Response(response)
  }
}
