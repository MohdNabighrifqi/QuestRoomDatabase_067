package com.example.roomlocaldb.ui.MahasiswaViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocaldb.data.entity.Mahasiswa
import com.example.roomlocaldb.repository.LocalRepositoryMhs
import com.example.roomlocaldb.repository.repositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: repositoryMhs) : ViewModel() {
    var uiState by mutableStateOf(MhsUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateUIState(mahasiswaEvent: MahasiswaEvent) {
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
    //Validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isBlank()) "NIM tidak boleh kosong" else null,
            nama = if (event.nama.isBlank()) "Nama tidak boleh kosong" else null,
            jenisKelamin = if (event.jenisKelamin.isBlank()) "Jenis Kelamin tidak boleh kosong" else null,
            alamat = if (event.alamat.isBlank()) "Alamat tidak boleh kosong" else null,
            kelas = if (event.kelas.isBlank()) "Kelas tidak boleh kosong" else null,
            angkatan = if (event.angkatan.isBlank()) "Angkatan tidak boleh kosong" else null,
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent

        if(validateFields()){
            viewModelScope.launch {
                try{
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(), //Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackbarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
){
    fun isValid(): Boolean {
        return nim != null && nama != null && jenisKelamin != null &&
                alamat != null && kelas != null && angkatan != null
    }
}

//Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

//data class variable yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)