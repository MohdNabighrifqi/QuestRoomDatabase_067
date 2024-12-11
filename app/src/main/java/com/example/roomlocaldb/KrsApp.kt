package com.example.roomlocaldb

import android.app.Application
import com.example.roomlocaldb.dependenciesinjection.ContainerApp

class KrsApp: Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)
    }
}