package de.burnynet.lastfmapi.http

import collection.mutable.ListBuffer
import de.burnynet.lastfmapi.{Utility, ApiAccount}


/**
 *
 * @author tobi
 */

trait ApiService {
  def apiAccount : ApiAccount

  def baseUrl() : String

  def service(call:Call): xml.Document


  def signCall(call:Call) : String = {
    val sorted = call.parameters.toSeq.sorted.toMap;
    // build signature string
    val s = sorted.foldLeft(ListBuffer[String]()){
      case (x:ListBuffer[String], (key:String, value:String)) => x += (key + value)
    }.reduceLeft (_ + _) + apiAccount.secret
    // Must be UTF-8 encoded
    Utility.md5SumString(s.getBytes("UTF-8"))
  }

}
