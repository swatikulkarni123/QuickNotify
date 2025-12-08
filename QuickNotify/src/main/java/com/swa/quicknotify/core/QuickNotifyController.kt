package com.swa.quicknotify.core

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

object QuickNotifyController {
    // a queue or single current message
    val currentMessage = mutableStateOf<QuickNotifyMessage?>(null)

    fun show(message: QuickNotifyMessage) {
        currentMessage.value = message
    }

    fun clear() {
        currentMessage.value = null
        QuickNotifyOverlay.content.value = null
        //Log.d("QuickToast", "clear called")
    }
}

// message data
data class QuickNotifyMessage(
    val text: String,
    val icon: ImageVector? = null,
    val durationMs: Long = 2000L,
    val kind: QuickNotifyKind = QuickNotifyKind.Toast
)

enum class QuickNotifyKind { Toast, Snackbar, Dialog }
