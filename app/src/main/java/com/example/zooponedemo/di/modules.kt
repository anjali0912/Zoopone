package com.example.zooponedemo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zooponedemo.data.db.AppDatabase
import com.example.zooponedemo.data.db.entity.Receivers
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.data.repository.ContactRepository
import com.example.zooponedemo.data.repository.IContactRepository
import com.example.zooponedemo.ui.contact.ContactViewModel
import com.example.zooponedemo.util.DATABASE_NAME
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val databaseModules = module {
    single {

        val CALLBACK: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                readArrayOfJsonObject(get() as AppDatabase, get() as Context, get() as ContactViewModel)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }

        Room.databaseBuilder(
                get(),
                AppDatabase::class.java, DATABASE_NAME
        )
                .fallbackToDestructiveMigration()
                .addCallback(CALLBACK)
                .build()

    }

    single(createOnStart = false) {
        val database: AppDatabase = get()
        database.contactDao()
    }
}

val repositoryModules = module {
    single<IContactRepository> { ContactRepository(get()) }
}

val viewModelModules = module {
    viewModel { ContactViewModel(get()) }
}

private fun readArrayOfJsonObject(db: AppDatabase, context: Context, contactViewModel: ContactViewModel) {
    var senderList = ArrayList<SenderEntity>()
    val bufferReader = context?.assets?.open("ContactsJson.json")?.bufferedReader()
    val json_string = bufferReader.use {
        it?.readText()
    }
    val jsonArray = JSONArray(json_string);
    for (i in 0..jsonArray.length() - 1) {
        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
        val name = jsonObject.getString("name")
        val number = jsonObject.getString("number")
        var sender = SenderEntity(0, name, number.toLong(), ArrayList())
        senderList.add(sender)
    }
    contactViewModel.insertSender(senderList)
}

