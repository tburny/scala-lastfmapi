package de.burnynet.lastfmapi.http

/**
 *
 * @author tobi
 */

class FileSystemApiService extends ApiService{

  def baseUrl() = getClass.getResource("").toString

  def apiAccount = null

  def service(call: Call) = null
}
