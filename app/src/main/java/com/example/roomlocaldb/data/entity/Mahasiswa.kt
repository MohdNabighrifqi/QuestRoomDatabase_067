package com.example.roomlocaldb.data.entity

import android.hardware.biometrics.BiometricManager.Strings
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mahsiswa ")
data class Mahasiswa(
    @PrimaryKey
    val nim :String,
    val nama :String,
    val alamat :String,
    val jenisKelamin :String,
    val kelas :String,
    val angkatan :String
)
