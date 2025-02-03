package com.mr.levelist

import android.app.Application
import com.example.common.util.TimberReleaseTree
import com.mr.levelist.core.data.di.dataModule
import com.mr.levelist.core.data.di.firebaseModule
import com.mr.levelist.core.domain.RemoteConfigManager
import com.mr.levelist.di.appModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class LevelistApplication : Application() {

    private val remoteConfigManager: RemoteConfigManager by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LevelistApplication)
            modules(listOf(appModule, dataModule, firebaseModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }

        remoteConfigManager.initializeRemoteConfig()
    }
}