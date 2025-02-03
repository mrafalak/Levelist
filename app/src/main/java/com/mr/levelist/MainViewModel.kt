package com.mr.levelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.util.AppLogs
import com.example.common.util.debug
import com.example.common.util.error
import com.mr.levelist.core.domain.FeatureFlagManager
import com.mr.levelist.core.domain.model.FeatureFlag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val featureFlagManager: FeatureFlagManager
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
        .onStart {
            getFlags()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    private val _featureFlags = MutableStateFlow<List<FeatureFlag>>(emptyList())
    val featureFlags: StateFlow<List<FeatureFlag>> = _featureFlags

    private fun getFlags() {
        viewModelScope.launch {
            _isLoading.value = true
            val flags = featureFlagManager.getAllFlags().first()
            if (flags.isNotEmpty()) {
                _featureFlags.value = flags
                _isLoading.value = false
                Timber.debug<MainViewModel>(AppLogs.FEATURE_FLAGS_IMPORTED, flags)
            } else {
                Timber.debug<MainViewModel>(AppLogs.FEATURE_FLAGS_REMOTE_NOT_IMPORTED)
                getDefaultFlags()
            }
        }
    }

    private fun getDefaultFlags() {
        viewModelScope.launch {
            _isLoading.value = true
            val defaultFlags = featureFlagManager.getAndSaveDefaultFlags().first()
            if (defaultFlags.isNotEmpty()) {
                _featureFlags.value = defaultFlags
                _isLoading.value = false
                Timber.debug<MainViewModel>(AppLogs.FEATURE_FLAGS_IMPORTED_DEFAULT, defaultFlags)
            } else {
                // TODO show error on UI
                _isLoading.value = false
                Timber.error<MainViewModel>(AppLogs.FEATURE_FLAGS_NOT_IMPORTED)
            }
        }
    }
}