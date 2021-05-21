package com.example.zooponedemo.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.zooponedemo.data.db.entity.SenderEntity
import kotlinx.coroutines.flow.Flow

interface IContactRepository {

    /**
     * Fetch the data from database
     */
    fun fetchDataFromDB(): Flow<List<SenderEntity>>

    /**
     * Fetch the count of database
     */
    fun fetchCountOfDB(): Int

    /**
     * Insert the list of contacts in the database.
     */
    suspend fun insert(data: List<SenderEntity>): LongArray

    /**
     * Insert the contact in the database.
     */
    suspend fun insertReceiver(data: SenderEntity)
}