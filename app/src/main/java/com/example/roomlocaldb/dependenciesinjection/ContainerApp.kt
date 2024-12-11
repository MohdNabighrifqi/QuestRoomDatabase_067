package com.example.roomlocaldb.dependenciesinjection

import android.content.Context
import com.example.roomlocaldb.data.database.KrsDatabase
import com.example.roomlocaldb.repository.LocalRepositoryMhs
import com.example.roomlocaldb.repository.repositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: repositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs: repositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}