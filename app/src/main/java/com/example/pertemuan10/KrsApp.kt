package com.example.pertemuan10

import android.app.Application
import com.example.pertemuan10.dependencisinjection.ConstainerApp
import com.example.pertemuan10.dependencisinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var  containerApp:  ConstainerApp // Fungsinya untuk menyimpan instance

    override fun onCreate() {
        super.onCreate()
        containerApp = ConstainerApp(this) // Membuat instance dari ConstainerApp
        // instance adalah object yang dibuat dari class
    }

}