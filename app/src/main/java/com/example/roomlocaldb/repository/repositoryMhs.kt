package com.example.roomlocaldb.repository

import com.example.roomlocaldb.data.entity.Mahasiswa

interface repositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}