package de.burnynet.lastfmapi

import http._

/**
 *
 * @author tobi
 */

trait Configuration {
  def account : ApiAccount
  def apiService : ApiService
}