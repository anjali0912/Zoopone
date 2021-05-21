package com.example.zooponedemo.data.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.zooponedemo.data.db.entity.SenderEntity
import kotlinx.coroutines.flow.Flow

/**
 * PeopleDao manages all the database queries.
 */
@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun fetchContacts(): Flow<List<SenderEntity>>

    @Query("SELECT COUNT(*) FROM contact")
    fun numberOfRecordsInDatabase(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contacts: List<SenderEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceiver(senderEntity: SenderEntity)

}