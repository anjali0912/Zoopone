package com.example.zooponedemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zooponedemo.data.db.dao.ContactDao
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.util.Converters
import com.example.zooponedemo.util.DATABASE_VERSION

/**
 * AppDatabase manages all the database configuration and transaction.
 */
@TypeConverters(Converters::class)
@Database(
    entities = [SenderEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
