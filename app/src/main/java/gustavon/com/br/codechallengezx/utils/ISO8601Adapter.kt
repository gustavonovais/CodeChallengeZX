package gustavon.com.br.codechallengezx.utils

import com.apollographql.apollo.CustomTypeAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gustavon on 31/03/18.
 */
class ISO8601Adapter : CustomTypeAdapter<Date> {
    override fun decode(value: String): Date {
        try {
            return ISO8601.parse(value)
        } catch (e: ParseException) {
            throw IllegalArgumentException("$value is not a valid ISO 8601 date", e)
        }

    }

    override fun encode(value: Date): String {
        return ISO8601.format(value)
    }

    companion object {
        private val ISO8601 = SimpleDateFormat(format)
    }
}