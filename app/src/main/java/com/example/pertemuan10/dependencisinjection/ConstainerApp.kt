package com.example.pertemuan10.dependencisinjection

import android.content.Context
import com.example.pertemuan10.repository.LocalRepositoryMhs
import com.example.pertemuan10.repository.RepositoryMhs
import com.example.pertemuan10.data.database.KrsDatabase

interface InterfaceContainerApp{
    val repositoryMhs : RepositoryMhs
}

class ConstainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}