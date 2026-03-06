package com.swa.quicknotify.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

/**
 * Cross-platform composable host for QuickNotify notifications.
 *
 * Wrap your root composable with this on **iOS**, **Desktop**, and **Web**.
 * On Android it is optional — the library auto-attaches via App Startup.
 *
 * ### Example (all non-Android platforms)
 * ```kotlin
 * @Composable
 * fun App() {
 *     QuickNotifyHost {
 *         MyAppContent()
 *     }
 * }
 * ```
 *
 * ### Desktop (main.kt)
 * ```kotlin
 * fun main() = application {
 *     Window(onCloseRequest = ::exitApplication) {
 *         QuickNotifyHost {
 *             App()
 *         }
 *     }
 * }
 * ```
 *
 * ### iOS (MainViewController.kt)
 * ```kotlin
 * fun MainViewController() = ComposeUIViewController {
 *     QuickNotifyHost {
 *         App()
 *     }
 * }
 * ```
 *
 * ### Web (main.kt)
 * ```kotlin
 * fun main() {
 *     onWasmReady {
 *         CanvasBasedWindow("MyApp") {
 *             QuickNotifyHost {
 *                 App()
 *             }
 *         }
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
