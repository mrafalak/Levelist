package com.mr.levelist.core.data.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mr.levelist.core.data.Config.REMOTE_CONFIG_FETCH_INTERVAL_SECONDS
import com.mr.levelist.core.data.Config.remoteConfigDisabledDefaultFlags
import com.mr.levelist.core.data.Config.remoteConfigEnabledDefaultFlags
import com.mr.levelist.core.domain.RemoteConfigManager
import com.mr.levelist.core.data.feature_flag.RemoteConfigManagerImpl
import com.mr.levelist.core.domain.BuildConfig
import org.koin.dsl.module

val firebaseModule = module {
    single<RemoteConfigManager> {
        RemoteConfigManagerImpl(get(), get(), BuildConfig.REMOTE_CONFIG_ENABLED)
    }
    single<FirebaseRemoteConfig> {
        Firebase.remoteConfig.apply {
            if (BuildConfig.REMOTE_CONFIG_ENABLED) {
                setDefaultsAsync(remoteConfigEnabledDefaultFlags)
                setConfigSettingsAsync(
                    remoteConfigSettings {
                        minimumFetchIntervalInSeconds = REMOTE_CONFIG_FETCH_INTERVAL_SECONDS
                    }
                )
            } else {
                setDefaultsAsync(remoteConfigDisabledDefaultFlags)
            }
        }
    }
}