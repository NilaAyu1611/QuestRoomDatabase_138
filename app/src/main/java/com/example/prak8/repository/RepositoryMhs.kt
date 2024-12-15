package com.example.prak8.repository

import com.example.prak8.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>        // mendapatkan semua data mhs dlm bentuk aliran data flow

}
