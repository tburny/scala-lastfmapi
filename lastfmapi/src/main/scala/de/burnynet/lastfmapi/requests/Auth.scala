package de.burnynet.lastfmapi.requests

import de.burnynet.lastfmapi.Configuration
import de.burnynet.lastfmapi.types.Session
import de.burnynet.lastfmapi.http.{AuthenticatedCall, ApiError, Call}

/**
 * Class representing the "auth" package
 * @author tobi
 */

class Auth(configuration:Configuration) {
  val apiService = configuration.apiService


  def getToken() : Either[ApiError, String] = {
    val call = new Call(Auth.PACKAGE, "getToken", Map())
    // first child node of xml is <token>
    apiService.service(call).result({xml => xml.text})
  }

  def getSession(token:String) : Either[ApiError, Session] = {
    val call = new AuthenticatedCall(Auth.PACKAGE, "getSession", null, Map("token" -> token))
    // first child node of xml is <token>
    apiService.service(call).result(Session.apply)
  }

  def getAuthenticationUrl() : String = "http://www.last.fm/api/auth/?api_key=" + configuration.account.key

  def getWebAuthenticationUrl(callbackUrl:String) : String = getAuthenticationUrl() + "&cb=" + callbackUrl
  def getDesktopAuthenticationUrl(token:String) : String = getAuthenticationUrl() + "&token=" + token
}
object Auth {
  private val PACKAGE = "auth"

  def apply(configuration:Configuration) = {
    new Auth(configuration)
  }
}