package com.swa.quicknotify.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView

/**
 * Android-specific overlay that auto-attaches to the first Activity.
 * Enabled automatically via [QuickNotifyInitializer] (App Startup).
 */
object QuickNotifyOverlay {

    val content: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)
    private var attached = false

    fun init(app: Application) {
        if (attached) return
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {
                attachOverlay(activity)
                app.unregisterActivityLifecycleCallbacks(this)
            }
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    private fun attachOverlay(activity: Activity) {
        if (attached) return
        attached = true

        val overlayView = ComposeView(activity)
        activity.addContentView(
            overlayView,
            android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            )
        )
        overlayView.setContent {
            val currentContent by content
            currentContent?.invoke()
        }
    }
}
