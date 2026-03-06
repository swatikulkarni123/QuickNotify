package com.swa.quicknotify.core

/**
 * iOS: No automatic overlay attachment.
 * Notifications are rendered via [QuickNotifyHost] composable.
 *
 * Wrap your UIViewController content:
 * ```kotlin
 * fun MainViewController() = ComposeUIViewController {
 *     QuickNotifyHost {
 *         App()
 *     }
 * }
 * ```
 */
internal actual fun notifyPlatformOverlay() {
    // No-op: QuickNotifyHost composable handles rendering on iOS
}

internal actual fun clearPlatformOverlay() {
    // No-op: state clearing via QuickNotifyController.currentMessage is sufficient
}
