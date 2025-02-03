package com.mr.levelist.core.domain

import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.Flow

interface FeatureFlagManager {
    fun getAllFlags(): Flow<List<FeatureFlag>>
    fun getAndSaveDefaultFlags(): Flow<List<FeatureFlag>>
    fun getFlag(key: String): Flow<FeatureFlag>
}