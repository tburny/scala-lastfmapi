package de.burnynet.lastfmapi.http

import collection.mutable.ListBuffer
import de.burnynet.lastfmapi.{ApiAccount, Md5Utility}

/**
 * @see [[de.burnynet.lastfmapi.http.Call]]
 *      A signed
 * @param sessionKey Authenticated session key of the user
 * @param isWriteRequest If the request is a write request, a HTTP POST will be used
 */
class AuthenticatedCall(
                         override val apiPackage: String,
                         override val apiMethod: String,
                         val sessionKey: String,
                         override val parameters: Map[String, String],
                         val isWriteRequest: Boolean = false
                         ) extends Call(apiPackage, apiMethod, parameters) {

  /**
   *
   * @param apiAccount
   * @return A [[scala.collection.Map]][String,String] containing all parameters required to perform the request
   *         including ones like "method" or "session". Does not include api key and secret for convenience reasons
   */
  override def getParameterMap(apiAccount: ApiAccount): Map[String, String] = {
    val params = insertSessionKey(super.getParameterMap(apiAccount))
    params + ("api_sig" -> AuthenticatedCall.signCall(params, apiAccount))
  }

  /**
   * Inserts the "session" parameter into parameter map if this call is not an auth.getSession call
   * @param params T
   */
  private def insertSessionKey(params: Map[String, String]): Map[String, String] = {
    val method = params("method").toLowerCase
    method match {
      case "auth.getsession" => params
      case _ => params + ("session" -> sessionKey)
    }
  }
}

object AuthenticatedCall {
  def signCall(parameters: Map[String, String], apiAccount: ApiAccount): String = {
    //need to sort parameters by key
    val sorted = scala.collection.immutable.SortedMap(parameters.toArray: _*)
    // build signature string
    val s = sorted.foldLeft(ListBuffer[String]()) {
      case (x: ListBuffer[String], (key: String, value: String)) => x += (key + value)
    }.reduceLeft(_ + _) + apiAccount.secret

    //FIXME
    println(s)
    // Must be UTF-8 encoded
    Md5Utility.md5SumString(s.getBytes("UTF-8"))
  }

}
