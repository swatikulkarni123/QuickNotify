package com.swa.quicknotify.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement

/**
 * Web (WasmJS) zero-setup overlay.
 *
 * Works the same as Android's QuickNotifyOverlay — no user setup required.
 * When QuickNotify.showToast() (or any show* method) is called, it automatically
 * creates a full-screen transparent canvas overlay on top of the existing page
 * content and renders notifications into it.
 *
 * Call QuickNotify.showToast("Hello!") directly — no wrapping needed.
 */
internal object QuickNotifyWebOverlay {

    val content = mutableStateOf<(@Composable () -> Unit)?>(null)
    private var attached = false

    @OptIn(ExperimentalComposeUiApi::class)
    fun init() {
        if (attached) return
        attached = true

        // Create a full-screen canvas sitting on top of everything
        val canvas = document.createElement("canvas") as HTMLCanvasElement
        canvas.id = "quicknotify-overlay-canvas"
        canvas.style.apply {
            position = "fixed"
            top = "0"
            left = "0"
            width = "100%"
            height = "100%"
            zIndex = "9999"
            pointerEvents = "none" // pass clicks through when no notification is shown
            background = "transparent"
        }
        document.body?.appendChild(canvas)

        // Render a Compose scene into the overlay canvas
        CanvasBasedWindow(canvasElementId = "quicknotify-overlay-canvas") {
            val currentContent by content
            // Enable pointer events only while a notification is active
            canvas.style.pointerEvents = if (currentContent != null) "auto" else "none"
            currentContent?.invoke()
        }
    }
}

internal actual fun notifyPlatformOverlay() {
    QuickNotifyWebOverlay.init()
    QuickNotifyWebOverlay.content.value = { QuickNotifyHostInternal() }
}

internal actual fun clearPlatformOverlay() {
    QuickNotifyWebOverlay.content.value = null
}
