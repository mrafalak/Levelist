package com.mr.levelist.core.domain

import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.Flow

interface FeatureFlagDataStore {
    fun getAllFlags(): Flow<List<FeatureFlag>>
    fun getFeatureFlag(key: String): Flow<FeatureFlag>
    suspend fun setFeatureFlag(key: String, value: Boolean)
    suspend fun removeFeatureFlag(key: String)
}