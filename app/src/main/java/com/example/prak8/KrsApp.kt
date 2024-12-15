package com.example.prak8

import android.app.Application
import com.example.prak8.dependenciesinjection.ContainerApp
import com.example.prak8.dependenciesinjection.InterfaceContainerApp

class KrsApp : Application() {
    // fungsinya untuk menyimpan instance containerapp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // membuat instance containerapp
        containerApp = ContainerApp(this)
        // instance adalah yang dibuat dari class
    }
}