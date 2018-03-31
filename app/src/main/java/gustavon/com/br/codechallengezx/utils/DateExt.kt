package gustavon.com.br.codechallengezx.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gustavon on 31/03/18.
 */

var format: String = "yyyy-MM-dd'T'HH:mm:ss"

fun fromISO8601UTC(dateStr: String): Date {
    val df1 = SimpleDateFormat(format)
    return df1.parse(dateStr)
}

fun getDateNow(): String {
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat(format)
    df.timeZone = tz
    return df.format(Date())
}