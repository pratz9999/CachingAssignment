package com.bayut.caching.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CalendarHelper {
    const val DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.S"
    const val INPUT_DATE_FORMAT = "EEE, MMM dd, yyyy"


    fun getConverted(iDateTime: String?, iCurrentFormat: String, iReturnFormat: String): String {
        val currentTime: String
        val dateFormat = SimpleDateFormat(iReturnFormat, Locale.US)

        currentTime = if (iDateTime == null) {
            val date = Date()
            dateFormat.format(date)
        } else {
            try {
                val originalFormat = SimpleDateFormat(iCurrentFormat, Locale.US)
                val date = originalFormat.parse(iDateTime)
                dateFormat.format(date!!)
            } catch (e: ParseException) {
                e.printStackTrace()
                return "NA"
            }
        }
        return currentTime
    }
}