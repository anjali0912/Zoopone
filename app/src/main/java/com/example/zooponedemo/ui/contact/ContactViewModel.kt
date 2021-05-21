package com.example.zooponedemo.ui.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.data.repository.IContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class ContactViewModel (private val repository: IContactRepository) : ViewModel() {

    companion object {
        private val TAG = ContactViewModel::class.java.simpleName
    }

    var senderList = ArrayList<SenderEntity>()

    init {

    }

    fun insertReceiver(senderEntity: SenderEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertReceiver(senderEntity)
            }
        }
    }

    fun insertSender(senderEntity: ArrayList<SenderEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(senderEntity)
            }
        }
    }

    /**
     * Provides the data to the view in the form of live data.
     */
    val contacts: LiveData<List<SenderEntity>> get() = repository.fetchDataFromDB().asLiveData()

    fun getRandomNumberString(): String? {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        val rnd = Random()
        val number: Int = rnd.nextInt(999999)

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number)
    }
}
