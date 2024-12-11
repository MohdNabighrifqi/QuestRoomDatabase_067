package com.example.roomlocaldb.repository

import com.example.roomlocaldb.data.dao.MahasiswaDao
import com.example.roomlocaldb.data.entity.Mahasiswa

class LocalRepositoryMhs( private val mahasiswaDao: MahasiswaDao) :repositoryMhs
{
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

}