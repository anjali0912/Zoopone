package com.example.zooponedemo.util

import com.example.zooponedemo.util.Mapper.SIMPLE_DATE_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    /**
     * Gets the date time in standard format.
     */
    fun getStandardTime(timeStamp: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.US)
        val date: Date = SIMPLE_DATE_FORMAT.parse(timeStamp)

        return outputFormat.format(date)
    }
}