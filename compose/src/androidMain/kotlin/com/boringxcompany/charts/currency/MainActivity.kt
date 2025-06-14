package com.boringxcompany.charts.currency

import ComposeEntryPoint
import Dependencies
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private lateinit var dependencies: Dependencies

    override fun onCreate(savedInstanceState: Bundle?) {
        println("MainActivity.onCreate()")
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        println("MainActivity after super.onCreate()")

        dependencies = (application as MainApplication).dependencies

        setContent {
            ComposeEntryPoint(dependencies)
        }
    }
}
