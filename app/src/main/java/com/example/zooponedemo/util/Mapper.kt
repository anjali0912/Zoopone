package com.example.zooponedemo.util

import java.text.SimpleDateFormat

/**
 * Mapper provides modified data to the view.
 */
object Mapper {

    val SIMPLE_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy HH.mm.ss")

    @JvmStatic
    fun setInitialLetter(name: String?): String? {
        return name?.first().toString()
    }

    @JvmStatic
    fun getUpdatedDate(eventDate: String?): String? {
        return eventDate?.let { DateUtil.getStandardTime(it) }
    }

}