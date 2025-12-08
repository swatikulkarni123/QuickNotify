package com.swa.quicknotify.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.swa.quicknotify.toast.QuickToast
import kotlinx.coroutines.delay

@Composable
fun QuickNotifyHostInternal() {
    var isVisible by remember { mutableStateOf(false) }
    val msgs = QuickNotifyController.currentMessage
    LaunchedEffect(msgs) {
        isVisible = true
        delay(msgs.value?.durationMs?:0)
        isVisible = false
        delay(100)
        QuickNotifyController.clear()
    }

    AnimatedVisibility(visible = isVisible) {
        QuickToast(message = msgs.value?.text, icon = msgs.value?.icon)
    }

}



