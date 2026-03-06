package com.swa.quicknotify.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

/**
 * **Optional** composable host for QuickNotify notifications.
 *
 * QuickNotify auto-attaches a global overlay on all platforms — you do NOT need
 * this composable to use the library. Simply call `QuickNotify.showToast(...)` anywhere.
 *
 * This composable is provided only as an **alternative embedding strategy** if you
 * prefer to render notifications inside your own composable hierarchy rather than
 * in a separate overlay. For most apps, you don't need it at all.
 *
 * ### Without QuickNotifyHost (recommended — works on all platforms)
 * ```kotlin
 * // Android  → auto-attaches via App Startup
 * // Desktop  → auto-attaches via Swing glass pane
 * // iOS      → auto-attaches via UIKit child ViewController
 * // Web      → auto-attaches via canvas overlay
 *
 * QuickNotify.showToast("Hello!") // just call it anywhere
 * ```
 *
 * ### With QuickNotifyHost (optional alternative)
 * ```kotlin
 * @Composable
 * fun App() {
 *     QuickNotifyHost {
 *         MyAppContent()
 *     }
 * }
 * ```
 */
@Composable
fun QuickNotifyHost(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val currentMessage by QuickNotifyController.currentMessage
    Box(modifier = modifier.fillMaxSize()) {
        content()
        if (currentMessage != null) {
            QuickNotifyHostInternal()
        }
    }
}
