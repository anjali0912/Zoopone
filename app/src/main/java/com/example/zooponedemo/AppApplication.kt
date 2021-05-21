package com.example.zooponedemo

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.android.startKoin

class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // Configure the koin dependency injection.
        startKoin(
            this, listOf(
                databaseModules,
                repositoryModules,
                viewModelModules
            )
        )
    }
}