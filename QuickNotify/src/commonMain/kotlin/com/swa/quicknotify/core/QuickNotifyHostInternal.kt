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
internal fun QuickNotifyHostInternal() {
    var isVisible by remember { mutableStateOf(false) }
    val msgs = QuickNotifyController.currentMessage

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

    val currentMsg = msgs.value ?: return
    AnimatedVisibility(
        visible = isVisible,
        enter = currentMsg.overlayEnter,
        exit = currentMsg.overlayExit,
    ) {
        when (currentMsg.kind) {
            QuickNotifyKind.Overlay -> showCustomView(currentMsg)
            QuickNotifyKind.Toast -> QuickToast(
                message = currentMsg.text,
                icon = currentMsg.icon,
            )
            QuickNotifyKind.Snackbar -> QuickSnackbar(
                message = currentMsg.text ?: "",
                icon = currentMsg.icon,
            )
            QuickNotifyKind.Dialog -> QuickCustomDialog(
                onDismiss = { QuickNotifyController.clear() },
                topImage = currentMsg.dialogImage,
                title = currentMsg.dialogTitle ?: "",
                body = currentMsg.dialogBody ?: "",
                btn1Text = currentMsg.btn1Text,
                btn1Color = currentMsg.btn1Color,
                btn1Icon = currentMsg.btn1Icon,
                onBtn1Click = {
                    currentMsg.onBtn1Click?.invoke()
                    QuickNotifyController.clear()
                },
                btn2Text = currentMsg.btn2Text,
                btn2Color = currentMsg.btn2Color,
                btn2Icon = currentMsg.btn2Icon,
                onBtn2Click = {
                    currentMsg.onBtn2Click?.invoke()
                    QuickNotifyController.clear()
                },
                btn3Text = currentMsg.btn3Text,
                btn3Color = currentMsg.btn3Color,
                btn3Icon = currentMsg.btn3Icon,
                onBtn3Click = {
                    currentMsg.onBtn3Click?.invoke()
                    QuickNotifyController.clear()
                },
            )
        }
    }
}
