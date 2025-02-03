package com.mr.levelist.core.data


object Config {
    val remoteConfigEnabledDefaultFlags = R.xml.remote_config_defaults
    val remoteConfigDisabledDefaultFlags = mapOf(
        "enableFeatureGoal" to false
    )
    const val REMOTE_CONFIG_FETCH_INTERVAL_SECONDS = 3600L
}

object PreferencesKeys {
    const val FEATURE_FLAGS = "feature_flags"
}