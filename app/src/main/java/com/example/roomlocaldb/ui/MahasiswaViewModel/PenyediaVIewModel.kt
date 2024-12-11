package com.example.roomlocaldb.ui.MahasiswaViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomlocaldb.KrsApp

object PenyediaVIewModel {
    val Factory = viewModelFactory {
        initializer {
            MahasiswaViewModel(
                krsApp().containerApp.repositoryMhs
            )
        }
    }
}


fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as KrsApp)