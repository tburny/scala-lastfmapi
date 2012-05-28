package de.burnynet.lastfmapi

import http.HttpApiService
import requests.{Artist, Auth}

/**
 *
 * @author tobi
 */

object Main {

  def main(args: Array[String]) {
    val configuration = new Configuration {
      def account = new ApiAccount {
        def secret = ""
        // API sample
        def key = "b25b959554ed76058ac220b7b2e0a026"
      }

      def apiService = new HttpApiService(account)
    }
    // TODO
    println(Auth(configuration).getToken())
    Artist(configuration).getInfo("Cher")
  }
}
