package com.example.fitvibe.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val LOCAL_STORAGE = "locale_storage"

val sharedPreferencesModule = module {
    single(named("")) {
        androidContext().getSharedPreferences(LOCAL_STORAGE, Context.MODE_PRIVATE)
    }
}