package com.example.zooponedemo.data.db.entity

import android.os.Parcelable
import androidx.room.*
import com.example.zooponedemo.util.Converters
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "contact")
data class SenderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    val number: Long,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "receivers")
    var receivers: ArrayList<Receivers>
) : Parcelable

@Parcelize
data class Receivers(
    @ColumnInfo(name = "otp")
    val otp: Int,

    @ColumnInfo(name = "msg")
    val msg: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: String
) : Parcelable