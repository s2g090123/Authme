package com.example.authme

import android.app.Application
import com.example.authme.koin.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// TODO - replace with your api key
const val API_KEY = BuildConfig.API_KEY

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(module)
        }
    }
}