package com.swa.quicknotify.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.swa.quicknotify.custom_overlay.showCustomView
import com.swa.quicknotify.dialog.QuickCustomDialog
import com.swa.quicknotify.snackbar.QuickSnackbar
import com.swa.quicknotify.toast.QuickToast
import kotlinx.coroutines.delay

@Composable
fun QuickNotifyHostInternal() {
    var isVisible by remember { mutableStateOf(false) }
    var msgs = QuickNotifyController.currentMessage
    LaunchedEffect(msgs.value) {
        isVisible = true
        when (msgs.value?.kind) {
            QuickNotifyKind.Toast, QuickNotifyKind.Snackbar -> {
                delay(msgs.value?.durationMs ?: 2000)
                isVisible = false
                delay(100)
                QuickNotifyController.clear()
            }

            QuickNotifyKind.Overlay -> {
                if (msgs.value?.overlayAutoCancel == true) {
                    delay(msgs.value?.durationMs ?: 2000)
                    isVisible = false
                    delay(100)
                    QuickNotifyController.clear()
                }
            }

            else -> {}
        }
    }

    AnimatedVisibility(
        visible = isVisible && (msgs.value != null||msgs.value?.overlayContent!=null),
        enter = msgs.value?.overlayEnter!!,
        exit = msgs.value?.overlayExit!!
    ) {
        when (msgs.value?.kind) {
            QuickNotifyKind.Overlay -> showCustomView(msgs.value)
            QuickNotifyKind.Toast -> QuickToast(message = msgs.value?.text, icon = msgs.value?.icon)
            QuickNotifyKind.Snackbar -> QuickSnackbar(
                message = msgs.value?.text ?: "",
                icon = msgs.value?.icon
            )
            QuickNotifyKind.Dialog ->
                QuickCustomDialog(
                    onDismiss = { QuickNotifyController.clear() },
                    topImage = msgs.value?.dialogImage,
                    title = msgs.value?.dialogTitle ?: "",
                    body = msgs.value?.dialogBody ?: "",
                    btn1Text = msgs.value?.btn1Text,
                    btn1Color = msgs.value?.btn1Color ?: Color(0xFF1976D2),
                    btn1Icon = msgs.value?.btn1Icon,
                    onBtn1Click = {
                        msgs.value?.onBtn1Click?.invoke()
                        QuickNotifyController.clear()
                    },
                    btn2Text = msgs.value?.btn2Text,
                    btn2Color = msgs.value?.btn2Color ?: Color(0xFF388E3C),
                    btn2Icon = msgs.value?.btn2Icon,
                    onBtn2Click = {
                        msgs.value?.onBtn2Click?.invoke()
                        QuickNotifyController.clear()
                    },
                    btn3Text = msgs.value?.btn3Text,
                    btn3Color = msgs.value?.btn3Color ?: Color(0xFFD32F2F),
                    btn3Icon = msgs.value?.btn3Icon,
                    onBtn3Click = {
                        msgs.value?.onBtn3Click?.invoke()
                        QuickNotifyController.clear()
                    }
                )

            else -> {}
        }
    }

}



