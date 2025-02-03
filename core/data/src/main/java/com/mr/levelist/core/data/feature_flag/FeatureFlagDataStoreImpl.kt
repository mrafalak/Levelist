package com.mr.levelist.core.data.feature_flag

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.mr.levelist.core.domain.FeatureFlagDataStore
import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeatureFlagDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : FeatureFlagDataStore {

    override fun getAllFlags(): Flow<List<FeatureFlag>> {
        return dataStore.data.map { preferences ->
            preferences.asMap()
                .filter { (_, value) -> value is Boolean }
                .map { (key, value) ->
                    FeatureFlag(
                        feature = key.name,
                        enabled = value as Boolean
                    )
                }
                .ifEmpty { emptyList() }
        }
    }

    override fun getFeatureFlag(key: String): Flow<FeatureFlag> {
        val dataStoreKey = booleanPreferencesKey(key)
        return dataStore.data.map { preferences ->
            FeatureFlag(
                feature = key,
                enabled = preferences[dataStoreKey] ?: false
            )
        }
    }

    override suspend fun setFeatureFlag(key: String, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[dataStoreKey] = value
        }
    }

    override suspend fun removeFeatureFlag(key: String) {
        val dataStoreKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }
}