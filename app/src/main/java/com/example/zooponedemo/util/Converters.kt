package com.example.zooponedemo.util

import androidx.room.TypeConverter
import com.example.zooponedemo.data.db.entity.Receivers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromString(value: String): ArrayList<Receivers> {
//        val listType = object : TypeToken<ArrayList<String>>() {}.type
//        return Gson().fromJson(value, listType)

        val gson = Gson()
        val itemType = object : TypeToken<List<Receivers>>() {}.type
        return gson.fromJson<ArrayList<Receivers>>(value, itemType)
    }

    @TypeConverter
    fun fromList(list: ArrayList<Receivers>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}