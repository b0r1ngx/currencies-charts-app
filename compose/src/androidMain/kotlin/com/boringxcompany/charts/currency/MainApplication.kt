package com.boringxcompany.charts.currency

import Dependencies
import android.app.Application

class MainApplication : Application() {
    // TODO: lazy it if profitable (when some part of UI can draw before init for ComposeEntryPoint())
    val dependencies = Dependencies()

    override fun onCreate() {
        println("MainApplication onCreate()")
        super.onCreate()
        println("MainApplication after super.onCreate()")
    }
}
