package de.burnynet.lastfmapi.requests

import de.burnynet.lastfmapi.ApiAccount
import de.burnynet.lastfmapi.http.{Call, HttpApiService, ApiService}
import de.burnynet.lastfmapi.types.ArtistProfile


/**
 * Calls for artist
 * @author tobi
 */

object Artist {

  // FIXME temporary solution, somehow inject proper configuration
  val apiAccount = new ApiAccount {
    // From API samples
    def key = "b25b959554ed76058ac220b7b2e0a026"

    def secret = ""
  }

  val apiService: ApiService = new HttpApiService(apiAccount)

  def getInfo(name: String): ArtistProfile = {
    val call = new Call("artist", "getInfo", Map("artist" -> name))
    val response = apiService.service(call)
    ArtistProfile(response.dataXml)
  }

}
