package com.swa.quicknotify.core

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
    val text: String? = null,
    val icon: ImageVector? = null,
    val durationMs: Long = 2000L,
    val kind: QuickNotifyKind = QuickNotifyKind.Toast,

    // Overlay support
    val overlayContent: (@Composable (dismiss: () -> Unit) -> Unit)? = null,
    val overlayAutoCancel: Boolean = false,
    val overlayEnter: EnterTransition = fadeIn() + expandIn(),
    val overlayExit: ExitTransition = shrinkOut() + fadeOut(),
    val overlayAlignment: Alignment = Alignment.BottomCenter,

    // Dialog params
    val dialogTitle: String? = null,
    val dialogBody: String? = null,
    val dialogImage: Painter? = null,

    val btn1Text: String? = null,
    val btn1Color: Color = Color(0xFF1976D2),
    val btn1Icon: ImageVector? = null,
    val onBtn1Click: (() -> Unit)? = null,

    val btn2Text: String? = null,
    val btn2Color: Color = Color(0xFF388E3C),
    val btn2Icon: ImageVector? = null,
    val onBtn2Click: (() -> Unit)? = null,

    val btn3Text: String? = null,
    val btn3Color: Color = Color(0xFFD32F2F),
    val btn3Icon: ImageVector? = null,
    val onBtn3Click: (() -> Unit)? = null,
)

enum class QuickNotifyKind { Toast, Snackbar, Dialog, Overlay }
