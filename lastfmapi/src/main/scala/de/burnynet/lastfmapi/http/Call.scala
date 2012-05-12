package de.burnynet.lastfmapi.http

import de.burnynet.lastfmapi.ApiAccount

/**
 * Stores information on an API call. Class should not implement any internal logic of the last.fm API
 * functionality
 *
 * @param apiPackage The name of the package, e.g. "artist"
 * @param apiMethod The method to call
 * @param parameters The parameters of the API call, excluding method and session
 * @author tobi
 */
class Call(
            val apiPackage: String,
            val apiMethod: String,
            val parameters: Map[String, String]
            ) {

  val methodParameter = apiPackage + "." + apiMethod

  /**
   * @return A [[scala.collection.Map]][String,String] containing all parameters required to perform the request
   *         including ones like "method" or "session". Does not include api key and secret for convenience reasons
   */
  def getParameterMap(apiAccount: ApiAccount): Map[String, String] = {
    parameters +("method" -> methodParameter, "api_key" -> apiAccount.key)
  }

}



