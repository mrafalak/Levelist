package com.example.common.util

import timber.log.Timber

class TimberReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // TODO send errors to Firebase Analytics by priority
        // TODO send errors to Crashlytics
        // TODO remove println()
        println("$tag: $message")
    }
}