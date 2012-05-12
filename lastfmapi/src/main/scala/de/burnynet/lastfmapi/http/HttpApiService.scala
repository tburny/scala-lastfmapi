package de.burnynet.lastfmapi.http

import dispatch._
import scala.xml._
import de.burnynet.lastfmapi.ApiAccount

/**
 *
 * @author tobi
 */

class HttpApiService(val apiAccount: ApiAccount) extends ApiService {
  val http = new Http

  override def baseUrl() = "http://ws.audioscrobbler.com/2.0"

  def service(call: Call): Response = {
    val http = new Http
    var req = url(baseUrl())
    var params = call.getParameterMap(apiAccount)

    var response: NodeSeq = Text("")

    var request: Request = constructRequest(call, req, params)
    http(request <> {
      response = _
    })
    //returns the parsed xml response as NodeSeq
    Response(response)
  }

  private def constructRequest(call: Call, req: Request, params: Map[String, String]): Request = {
    val request: Request = call match {
      case authCall: AuthenticatedCall =>
        if (authCall.isWriteRequest) req <<< params else req <<? params
      case _ => req <<? params
    }
    //Enable gzip compression
    request.gzip
  }
}
