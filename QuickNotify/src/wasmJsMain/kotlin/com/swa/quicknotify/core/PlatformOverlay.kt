package com.swa.quicknotify.core

/**
 * Web (WasmJS): No automatic overlay attachment.
 * Notifications are rendered via [QuickNotifyHost] composable.
 *
 * Wrap your canvas content:
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
internal actual fun notifyPlatformOverlay() {
    // No-op: QuickNotifyHost composable handles rendering on Web
}

internal actual fun clearPlatformOverlay() {
    // No-op: state clearing via QuickNotifyController.currentMessage is sufficient
}
