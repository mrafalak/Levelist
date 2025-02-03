package com.mr.levelist.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.mr.levelist.core.data.PreferencesKeys
import com.mr.levelist.core.data.feature_flag.FeatureFlagDataStoreImpl
import com.mr.levelist.core.data.feature_flag.FeatureFlagManagerImpl
import com.mr.levelist.core.domain.FeatureFlagDataStore
import com.mr.levelist.core.domain.FeatureFlagManager
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<DataStore<Preferences>>(named(PreferencesKeys.FEATURE_FLAGS)) {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile(PreferencesKeys.FEATURE_FLAGS) }
        )
    }
    single<FeatureFlagManager> { FeatureFlagManagerImpl(get(), get()) }
    single<FeatureFlagDataStore> { FeatureFlagDataStoreImpl(get(named(PreferencesKeys.FEATURE_FLAGS))) }
}