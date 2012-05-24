package de.burnynet.lastfmapi

import java.text.SimpleDateFormat
import java.util.{Locale, Date}

/**
 *
 * @author tobi
 */

object DateTimeConverter {

  def convertIso8601Date(date:String) : Date =
    // SimpleDateFormat is NOT thread safe! Use English as locale becasue the dates in the API are english
    (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH)).parse("Tue, 29 Jan 2002 22:14:02")

}
