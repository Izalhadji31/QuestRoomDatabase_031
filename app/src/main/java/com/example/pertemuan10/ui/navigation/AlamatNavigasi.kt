package com.example.pertemuan10.ui.navigation

interface AlamatNavigasi {
    val route: String
    object  DestinasiHome : AlamatNavigasi {
        override val route = "home"
    }
}