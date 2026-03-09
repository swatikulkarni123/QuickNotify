package com.swa.quicknotify.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow

/**
 * iOS zero-setup overlay.
 *
 * Works the same as Android's QuickNotifyOverlay — no user setup required.
 * When QuickNotify.showToast() (or any show* method) is called, it automatically
 * finds the key UIWindow, creates a ComposeUIViewController, and adds it as a
 * child view controller on top of the root view controller.
 *
 * Call QuickNotify.showToast("Hello!") directly — no wrapping needed.
 */
@OptIn(ExperimentalForeignApi::class)
internal object QuickNotifyIosOverlay {

    val content = mutableStateOf<(@Composable () -> Unit)?>(null)
    private var overlayViewController: UIViewController? = null

    fun init() {
        if (overlayViewController != null) return
        attachOverlay()
    }

    private fun attachOverlay() {
        @Suppress("UNCHECKED_CAST")
        val keyWindow = UIApplication.sharedApplication.windows
            .filterIsInstance<UIWindow>()
            .lastOrNull { it.isKeyWindow }
            ?: return

        val rootVC = keyWindow.rootViewController ?: return

        val vc = ComposeUIViewController {
            val currentContent by content
            currentContent?.invoke()
        }

        overlayViewController = vc
        rootVC.addChildViewController(vc)
        vc.view.frame = keyWindow.bounds
        vc.view.backgroundColor = UIColor.clearColor
        vc.view.userInteractionEnabled = true
        rootVC.view.addSubview(vc.view)
        vc.didMoveToParentViewController(rootVC)
    }
}

internal actual fun notifyPlatformOverlay() {
    QuickNotifyIosOverlay.init()
    QuickNotifyIosOverlay.content.value = { QuickNotifyHostInternal() }
}

internal actual fun clearPlatformOverlay() {
    QuickNotifyIosOverlay.content.value = null
}
