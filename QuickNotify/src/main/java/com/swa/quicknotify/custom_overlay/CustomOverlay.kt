package com.swa.quicknotify.custom_overlay

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.swa.quicknotify.core.QuickNotifyController
import com.swa.quicknotify.core.QuickNotifyMessage
import com.swa.quicknotify.dialog.QuickCustomDialog

@Composable
fun showCustomView(value: QuickNotifyMessage?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = value!!.overlayAlignment // use alignment here
    ) {
        value.overlayContent?.invoke {
            QuickNotifyController.clear()
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    return this.clickable(
        interactionSource = MutableInteractionSource(),
        indication = null,
        onClick = onClick
    )
}