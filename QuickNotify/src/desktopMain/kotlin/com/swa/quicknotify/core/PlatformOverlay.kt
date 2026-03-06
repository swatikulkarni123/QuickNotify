package com.swa.quicknotify.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.awt.ComposePanel
import java.awt.Window
import javax.swing.JFrame
import javax.swing.SwingUtilities

/**
 * Desktop (JVM) zero-setup overlay.
 *
 * Works the same as Android's QuickNotifyOverlay — no user setup required.
 * When QuickNotify.showToast() (or any show* method) is called for the first time,
 * it automatically finds the active JFrame and attaches a transparent ComposePanel
 * as the glass pane so notifications appear on top of everything.
 *
 * Call QuickNotify.showToast("Hello!") directly — no wrapping needed.
 */
internal object QuickNotifyDesktopOverlay {

    val content = mutableStateOf<(@Composable () -> Unit)?>(null)
    private var attached = false

    fun init() {
        if (attached) return
        SwingUtilities.invokeLater {
            attachOverlay()
        }
    }

    private fun attachOverlay() {
        if (attached) return
        val frame = Window.getWindows()
            .filterIsInstance<JFrame>()
            .firstOrNull { it.isVisible }
            ?: return // Not yet visible — will retry on next show*() call

        attached = true

        val panel = ComposePanel()
        panel.isOpaque = false
        panel.setContent {
            val currentContent by content
            currentContent?.invoke()
        }

        frame.glassPane = panel
        frame.glassPane.isVisible = true
    }
}

internal actual fun notifyPlatformOverlay() {
    QuickNotifyDesktopOverlay.init()
    QuickNotifyDesktopOverlay.content.value = { QuickNotifyHostInternal() }
}

internal actual fun clearPlatformOverlay() {
    QuickNotifyDesktopOverlay.content.value = null
}
