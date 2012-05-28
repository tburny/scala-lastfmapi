package de.burnynet.lastfmapi.requests

import de.burnynet.lastfmapi.types.ArtistProfile
import de.burnynet.lastfmapi.Configuration
import de.burnynet.lastfmapi.http.{Call, ApiError}


/**
 * Calls for artist
 * @author tobi
 */

class Artist(configuration:Configuration) {

  val apiService = configuration.apiService

  def getInfo(name: String): Either[ApiError, ArtistProfile] = {
    val call = new Call("artist", "getInfo", Map("artist" -> name))
    apiService.service(call).result(ArtistProfile.apply)
  }
}

object Artist {

  def apply(lastFmApiConfiguration:Configuration) = {
    new Artist(lastFmApiConfiguration)
  }

}
