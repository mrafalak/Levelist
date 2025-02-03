package com.mr.levelist.core.domain

import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.Flow

interface RemoteConfigManager {
    fun initializeRemoteConfig()
    fun fetchAndSyncWithDataStore()
    fun getDefaultFlags(): Flow<List<FeatureFlag>>
}