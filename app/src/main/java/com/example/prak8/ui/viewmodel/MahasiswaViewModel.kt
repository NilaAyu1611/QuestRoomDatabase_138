package com.example.prak8.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak8.Data.entity.Mahasiswa
import com.example.prak8.repository.LocalRepositoryMhs
import com.example.prak8.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs): ViewModel(){

    var uiState by mutableStateOf(MhsUIState())

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if(event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if(event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jeniskelamin = if(event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if(event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if(event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if(event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs((currentEvent.toMahasiswaEntity()))
                    uiState = uiState.copy(
                            snackBarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )

                }
                catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "data gagal disimpan"
                    )
                }
            }

        }else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. periksa kembali data anda"
            )
        }
    }
    fun resetSbackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

// untuk merubah state/perubahan
data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,        //mirip dengan popup
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jeniskelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
){
    fun isValid():Boolean{          // untuk validasi bair ndak kosong
        return nim == null && nama == null && jeniskelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

// menyimoan inout dorm kedalam entity
fun MahasiswaEvent.toMahasiswaEntity():Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jeniskelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

// data class variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)