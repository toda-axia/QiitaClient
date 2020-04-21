package com.wumpuss.qiitaclient.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsUtils {
    private var firebaseAnalytics: FirebaseAnalytics? = null

    private fun getAnalytics(context: Context): FirebaseAnalytics {
        firebaseAnalytics?.let {
            return it
        } ?: run {
            return FirebaseAnalytics.getInstance(context).apply {
                firebaseAnalytics = this
            }
        }
    }

    private fun sendLog(context: Context, eventName: String, bundle: Bundle) {
        getAnalytics(context).logEvent(eventName, bundle)
    }

    fun sendAppStartLog(context: Context) {
        val bundle = Bundle().apply {
            putString("screen", "home")
            putString("item_id", "app_launch")
        }
        sendLog(context, "daily_count", bundle)
    }
}