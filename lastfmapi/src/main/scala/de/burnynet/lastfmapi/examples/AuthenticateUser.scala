package de.burnynet.lastfmapi.examples

import de.burnynet.lastfmapi.requests.Auth

/**
 *
 * @author tobi
 */

class AuthenticateUser {

  def main(args: Array[String]) {
    // NOTE: You have to fill in your own key/secret for now
    val configuration = new ExampleConfiguration
    // A shorthand function. Still unsure if it should stay this way
    val auth = Auth(configuration)

    /*
    * For web authentication:
    * println("Copy the following url to your browser and grant access:")
    * println(auth.getWebAuthenticationUrl())
    * It will redirect you to the defined callback page, with the token as parameter.
    * use
    * val token = valueFromTokenParameter
    * and
    *  print(auth.getSession(token))
    */

    /*
    * Because it is more convenient here to get the token programatically
    * we will do that and call auth.getSession using that token
    */
    auth.getToken() match {
      case Right(token) => {
        println("token is " + token)
        println("Copy the following url to your browser and grant access:")
        // In a real web world, you would use getWebAuthenticationUrl
        println(auth.getDesktopAuthenticationUrl(token))
        readChar()
        print(auth.getSession(token))
      }

      case Left(x) => null
    }
  }

}
