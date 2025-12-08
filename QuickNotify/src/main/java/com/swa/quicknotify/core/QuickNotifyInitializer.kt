package com.swa.quicknotify.core

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class QuickNotifyInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        QuickNotifyOverlay.init(context.applicationContext as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}