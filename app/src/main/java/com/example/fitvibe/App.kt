package com.example.fitvibe

import android.app.Application
import com.example.fitvibe.di.allModules
import com.example.fitvibe.utils.timber.TimberTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(allModules)
        }
        val hm = HashMap<String, Int>()
        Timber.plant(TimberTree())
    }

}