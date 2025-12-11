package com.swa.quicknotify.core

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.swa.quicknotify.core.QuickNotifyController.currentMessage

object QuickNotify {

    fun showToast(
        message: String? = "",
        icon: ImageVector? = null,
        duration: Long = 2000L,
        customUi: (@Composable () -> Unit)? = null,
        ) {
        QuickNotifyController.show(
            QuickNotifyMessage(
                text = message,
                icon = icon,
                durationMs = duration,
                kind = QuickNotifyKind.Toast,
            )
        )

        QuickNotifyOverlay.content.value = {
            QuickNotifyHostInternal()
        }

    }
    fun showSnackbar(
        message: String,
        icon: ImageVector? = null,
        duration: Long = 3000L
    ) {
        QuickNotifyController.show(
            QuickNotifyMessage(
                text = message,
                icon = icon,
                durationMs = duration,
                kind = QuickNotifyKind.Snackbar
            )
        )
        QuickNotifyOverlay.content.value = { QuickNotifyHostInternal() }
    }
    fun showDialog(
        title: String,
        body: String,
        image: Painter? = null,
        btn1Text: String? = null,
        btn1Color: Color = Color(0xFF1976D2),
        btn1Icon: ImageVector? = null,
        onBtn1Click: (() -> Unit)? = null,
        btn2Text: String? = null,
        btn2Color: Color = Color(0xFF388E3C),
        btn2Icon: ImageVector? = null,
        onBtn2Click: (() -> Unit)? = null,
        btn3Text: String? = null,
        btn3Color: Color = Color(0xFFD32F2F),
        btn3Icon: ImageVector? = null,
        onBtn3Click: (() -> Unit)? = null
    ) {
        QuickNotifyController.show(
            QuickNotifyMessage(
                kind = QuickNotifyKind.Dialog,
                dialogTitle = title,
                dialogBody = body,
                dialogImage = image,
                btn1Text = btn1Text,
                btn1Color = btn1Color,
                btn1Icon = btn1Icon,
                onBtn1Click = onBtn1Click,
                btn2Text = btn2Text,
                btn2Color = btn2Color,
                btn2Icon = btn2Icon,
                onBtn2Click = onBtn2Click,
                btn3Text = btn3Text,
                btn3Color = btn3Color,
                btn3Icon = btn3Icon,
                onBtn3Click = onBtn3Click
            )
        )

        QuickNotifyOverlay.content.value = {
            QuickNotifyHostInternal()
        }
    }

    fun showOverlay(
        duration: Long = 2000,
        autoCancel: Boolean = false,
        enter: EnterTransition = fadeIn() + expandIn(),
        exit: ExitTransition = shrinkOut() + fadeOut(),
        overlayAlignment: Alignment = Alignment.BottomCenter,
        content: @Composable (dismiss: () -> Unit) -> Unit,
    ) {
        QuickNotifyController.show(
            QuickNotifyMessage(
                kind = QuickNotifyKind.Overlay,
                overlayContent = content,
                durationMs = duration,
                overlayAutoCancel = autoCancel,
                overlayEnter = enter,
                overlayExit = exit ,
                overlayAlignment = overlayAlignment
            )
        )

        QuickNotifyOverlay.content.value = { QuickNotifyHostInternal() }
    }

}