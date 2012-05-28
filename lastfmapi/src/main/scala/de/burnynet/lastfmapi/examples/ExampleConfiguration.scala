package de.burnynet.lastfmapi.examples

import de.burnynet.lastfmapi.{ApiAccount, Configuration}
import de.burnynet.lastfmapi.http.HttpApiService


/**
 *
 * @author tobi
 */

class ExampleConfiguration extends Configuration {
  def account = new ApiAccount {
    def secret = "[your secret here]"

    // API sample
    def key = "[your api key here]" //"b25b959554ed76058ac220b7b2e0a026"
  }

  def apiService = new HttpApiService(account)
}
