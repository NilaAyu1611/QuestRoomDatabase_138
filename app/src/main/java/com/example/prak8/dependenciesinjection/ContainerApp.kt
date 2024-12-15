package com.example.prak8.dependenciesinjection

import android.content.Context
import com.example.prak8.Data.database.krsDatabase
import com.example.prak8.repository.LocalRepositoryMhs
import com.example.prak8.repository.RepositoryMhs

interface InterfaceContainerApp{
    val repositoryMhs:RepositoryMhs
}

class ContainerApp(private val context:Context): InterfaceContainerApp{
    override val repositoryMhs:RepositoryMhs by lazy {
        LocalRepositoryMhs(krsDatabase.getDatabase(context).mahasiswaDao())
    }
}