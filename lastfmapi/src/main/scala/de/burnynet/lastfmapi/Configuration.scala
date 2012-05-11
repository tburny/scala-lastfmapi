package de.burnynet.lastfmapi

import http._

/**
 *
 * @author tobi
 */

trait Configuration {
  abstract def account : ApiAccount
  abstract def apiService : ApiService
}