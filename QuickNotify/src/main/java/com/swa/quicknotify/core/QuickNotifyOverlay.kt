package com.swa.quicknotify.core

import android.app.Application
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView

object QuickNotifyOverlay {

    // Holds the composable content for the overlay
    val content: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)
    private var attached = false

    fun init(app: Application) {
        if (attached) return

        // Wait for first activity to attach the overlay
        app.registerActivityLifecycleCallbacks(object :
            android.app.Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: android.os.Bundle?
            ) {
            }

            override fun onActivityStarted(activity: Activity) {
                attachOverlay(activity)
                app.unregisterActivityLifecycleCallbacks(this)
            }
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: android.os.Bundle
            ) {
            }

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
                android.view.ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        overlayView.setContent {
            val currentContent by content   // <-- KEY FIX
            currentContent?.invoke()
        }
    }
}
