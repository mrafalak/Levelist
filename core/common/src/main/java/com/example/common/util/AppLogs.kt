package com.example.common.util

object AppLogs {
    // Remote Config
    const val REMOTE_CONFIG_UPDATE_LISTENER_DISABLED = "Remote config listener is disabled."
    const val REMOTE_CONFIG_UPDATE_SKIP = "Skipping update key: %s with source: %s"
    const val REMOTE_CONFIG_UPDATE_SUCCESS = "Remote config successfully updated"
    const val REMOTE_CONFIG_UPDATE_LISTENER_ERROR =
        "Remote config update listener error with code: %s"

    const val REMOTE_CONFIG_FETCH_SKIPPED = "Remote config fetch skipped because it is disabled."
    const val REMOTE_CONFIG_FETCH_SUCCESS = "Remote config fetched successfully."
    const val REMOTE_CONFIG_FETCH_ERROR = "Failed to sync flags with message: %s"

    const val REMOTE_CONFIG_FLAGS_SAVED_IN_DATA_STORE =
        "Feature flags successfully saved in data store."

    const val REMOTE_CONFIG_SAVE_DEFAULT_FLAGS = "Save default feature flags in data store: %s"

    // Feature Flags
    const val FEATURE_FLAGS_IMPORTED = "Flags imported from datastore: %s"
    const val FEATURE_FLAGS_IMPORTED_DEFAULT = "Default flags was imported from datastore: %s"
    const val FEATURE_FLAGS_REMOTE_NOT_IMPORTED =
        "Flags not imported from remote config! Trying to import default flags."
    const val FEATURE_FLAGS_NOT_IMPORTED = "Flags not imported from datastore!"
}