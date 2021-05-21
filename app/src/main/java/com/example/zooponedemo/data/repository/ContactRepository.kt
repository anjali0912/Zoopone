package com.example.zooponedemo.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.zooponedemo.data.db.dao.ContactDao
import com.example.zooponedemo.data.db.entity.SenderEntity

class ContactRepository (private val contactDao: ContactDao) : IContactRepository {

    companion object {
        private val TAG = ContactRepository::class.java.simpleName
    }

    override fun fetchDataFromDB() = contactDao.fetchContacts()

    override fun fetchCountOfDB() = contactDao.numberOfRecordsInDatabase()

    override suspend fun insert(data: List<SenderEntity>) = contactDao.insert(data)

    override suspend fun insertReceiver(data: SenderEntity) = contactDao.insertReceiver(data)
}
