package com.swa.quicknotify.core

import android.util.Log
import androidx.compose.ui.graphics.vector.ImageVector

object QuickNotify {

    fun showToast(
        message: String,
        icon: ImageVector? = null,
        duration: Long = 2000L
    ) {
        Log.d("QuickToast", "show QuickNotify")
        QuickNotifyController.show(
            QuickNotifyMessage(
                text = message,
                icon = icon,
                durationMs = duration,
                kind = QuickNotifyKind.Toast
            )
        )

        QuickNotifyOverlay.content.value = {
            QuickNotifyHostInternal()
        }

    }
}