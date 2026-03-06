package com.swa.quicknotify.core

/**
 * Desktop (JVM): No automatic overlay attachment.
 * Notifications are rendered via [QuickNotifyHost] composable.
 *
 * Wrap your Window content:
 * ```kotlin
 * fun main() = application {
 *     Window(onCloseRequest = ::exitApplication) {
 *         QuickNotifyHost {
 *             App()
 *         }
 *     }
 * }
 * ```
 */
internal actual fun notifyPlatformOverlay() {
    // No-op: QuickNotifyHost composable handles rendering on Desktop
}

internal actual fun clearPlatformOverlay() {
    // No-op: state clearing via QuickNotifyController.currentMessage is sufficient
}
