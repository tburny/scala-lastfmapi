package de.burnynet.lastfmapi.http

import collection.mutable.ListBuffer
import de.burnynet.lastfmapi.{ApiAccount, Utility}

/**
 * Stores information on an API call. Class should not implement any internal logic of the last.fm API
 * functionality
 * @author tobi
 */

abstract class Call(
  val apiPackage : String,
  val apiMethod:String,
  val parameters: Map[String, String]
)

case class NormalCall(
    override val apiPackage: String,
    override val apiMethod:String,
    override val parameters: Map[String, String]
  ) extends Call(apiPackage,apiMethod,parameters)

case class AuthenticatedCall(
                 override val apiPackage: String,
                 override val apiMethod:String,
                 sessionKey: String,
                 override val parameters: Map[String, String],
                 isWriteRequest:Boolean = false
                 ) extends Call(apiPackage,apiMethod,parameters)
object Call {

  def apply(apiPackage : String, apiMethod:String, params: Map[String, String], sessionKey: String,
            isWriteRequest: Boolean = false) : Call = {
    new AuthenticatedCall(apiPackage,apiMethod,sessionKey, params, isWriteRequest)
  }
    def apply(apiPackage : String, apiMethod:String, params: Map[String, String], sign: Boolean = false,
            isWriteRequest: Boolean = false) : Call = {
    new NormalCall(apiPackage, apiMethod, params)
  }

}