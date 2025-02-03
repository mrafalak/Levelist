package com.mr.levelist.core.data.feature_flag

import com.example.common.util.AppLogs
import com.example.common.util.debug
import com.example.common.util.error
import com.example.common.util.info
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.mr.levelist.core.domain.FeatureFlagDataStore
import com.mr.levelist.core.domain.RemoteConfigManager
import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import timber.log.Timber

class RemoteConfigManagerImpl(
    private val remoteConfig: FirebaseRemoteConfig,
    private val featureFlagDataStore: FeatureFlagDataStore,
    private val isEnabled: Boolean
) : RemoteConfigManager {

    private val configUpdateListener = object : ConfigUpdateListener {
        override fun onUpdate(configUpdate: ConfigUpdate) {
            saveUpdatedFlagsToDataStore(configUpdate.updatedKeys)
        }

        override fun onError(error: FirebaseRemoteConfigException) {
            Timber.error<RemoteConfigManagerImpl>(
                AppLogs.REMOTE_CONFIG_UPDATE_LISTENER_ERROR,
                error.code,
                error
            )
        }
    }

    override fun initializeRemoteConfig() {
        CoroutineScope(Dispatchers.IO).launch {
            if (!isEnabled) {
                Timber.info<RemoteConfigManagerImpl>(AppLogs.REMOTE_CONFIG_UPDATE_LISTENER_DISABLED)
                Timber.info<RemoteConfigManagerImpl>(AppLogs.REMOTE_CONFIG_FETCH_SKIPPED)
            } else {
                remoteConfig.addOnConfigUpdateListener(configUpdateListener)
                fetchAndSyncWithDataStore()
            }
        }
    }

    override fun fetchAndSyncWithDataStore() {
        remoteConfig.fetchAndActivate()
            .addOnSuccessListener {
                Timber.info<RemoteConfigManagerImpl>(AppLogs.REMOTE_CONFIG_FETCH_SUCCESS)
                saveFlagsToDataStore(
                    flags = remoteConfig.all.mapValues { it.value.asBoolean() }
                )
            }
            .addOnFailureListener { exception ->
                Timber.error<RemoteConfigManagerImpl>(
                    AppLogs.REMOTE_CONFIG_FETCH_ERROR,
                    exception.message,
                    exception
                )
            }
    }

    override fun getDefaultFlags(): Flow<List<FeatureFlag>> {
        val flagsMap = remoteConfig.all.mapValues { it.value.asBoolean() }
        saveFlagsToDataStore(flagsMap)

        val flagsList = flagsMap
            .map { (key, value) ->
                FeatureFlag(
                    feature = key,
                    enabled = value
                )
            }

        return flowOf(flagsList)
    }

    private fun saveFlagsToDataStore(flags: Map<String, Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            flags.forEach { (key, value) ->
                featureFlagDataStore.setFeatureFlag(key, value)
            }
            Timber.info<RemoteConfigManagerImpl>(AppLogs.REMOTE_CONFIG_FLAGS_SAVED_IN_DATA_STORE)
        }
    }

    private fun saveUpdatedFlagsToDataStore(updatedKeys: Set<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            updatedKeys.forEach { key ->
                val value = remoteConfig.getValue(key)
                when (value.source) {
                    FirebaseRemoteConfig.VALUE_SOURCE_REMOTE,
                    FirebaseRemoteConfig.VALUE_SOURCE_DEFAULT -> {
                        featureFlagDataStore.setFeatureFlag(key, value.asBoolean())
                    }

                    else -> {
                        Timber.debug<RemoteConfigManagerImpl>(
                            AppLogs.REMOTE_CONFIG_UPDATE_SKIP,
                            key,
                            value.source
                        )
                    }
                }
            }
            Timber.info<RemoteConfigManagerImpl>(AppLogs.REMOTE_CONFIG_UPDATE_SUCCESS)
        }
    }
}