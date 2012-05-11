package de.burnynet.lastfmapi

import java.security.MessageDigest
import collection.mutable.ListBuffer

/**
 *
 * @author tobi
 */

object Utility {


  def md5SumString(bytes : Array[Byte]) : String = {
    val md5 = MessageDigest.getInstance("MD5")
    md5.reset()
    md5.update(bytes)

    md5.digest().map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}
  }
}
