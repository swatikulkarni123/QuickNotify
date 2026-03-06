package com.swa.quicknotify.core

internal actual fun notifyPlatformOverlay() {
    QuickNotifyOverlay.content.value = { QuickNotifyHostInternal() }
}

internal actual fun clearPlatformOverlay() {
    QuickNotifyOverlay.content.value = null
}
