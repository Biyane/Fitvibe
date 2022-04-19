package com.example.fitvibe

import android.app.Application
import com.example.fitvibe.utils.timber.TimberTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberTree())
    }

}