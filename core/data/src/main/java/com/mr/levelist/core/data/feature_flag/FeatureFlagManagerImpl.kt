package com.mr.levelist.core.data.feature_flag

import com.mr.levelist.core.domain.FeatureFlagDataStore
import com.mr.levelist.core.domain.FeatureFlagManager
import com.mr.levelist.core.domain.RemoteConfigManager
import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.Flow

class FeatureFlagManagerImpl(
    private val remoteConfigManager: RemoteConfigManager,
    private val featureFlagDataStore: FeatureFlagDataStore,
) : FeatureFlagManager {

    override fun getAllFlags(): Flow<List<FeatureFlag>> {
        return featureFlagDataStore.getAllFlags()
    }

    override fun getAndSaveDefaultFlags(): Flow<List<FeatureFlag>> {
        return remoteConfigManager.getDefaultFlags()
    }

    override fun getFlag(key: String): Flow<FeatureFlag> {
        return featureFlagDataStore.getFeatureFlag(key)
    }
}