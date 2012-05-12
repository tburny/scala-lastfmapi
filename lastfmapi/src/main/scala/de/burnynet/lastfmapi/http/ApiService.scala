package de.burnynet.lastfmapi.http

import de.burnynet.lastfmapi.ApiAccount


/**
 *
 * @author tobi
 */

trait ApiService {
  def apiAccount: ApiAccount

  def baseUrl(): String

  def service(call: Call): Response

}
