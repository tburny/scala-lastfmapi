package de.burnynet.lastfmapi.requests

import de.burnynet.lastfmapi.Configuration
import de.burnynet.lastfmapi.http.{ApiError, Call}

/**
 * Class representing the "auth" package
 * @author tobi
 */

class Auth(configuration:Configuration) {
  val apiService = configuration.apiService

  def getToken() : Either[ApiError, String] = {
    val call = new Call("auth", "getToken", Map())
    // first child node of xml is <token>
    apiService.service(call).result({xml => xml.text})
  }
}
object Auth {

  def apply(configuration:Configuration) = {
    new Auth(configuration)
  }
}