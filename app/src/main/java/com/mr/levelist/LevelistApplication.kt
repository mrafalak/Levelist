package com.mr.levelist

import android.app.Application
import com.mr.levelist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LevelistApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LevelistApplication)
            modules(appModule)
        }
    }
}