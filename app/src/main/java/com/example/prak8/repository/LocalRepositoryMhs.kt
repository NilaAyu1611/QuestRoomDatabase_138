package com.example.prak8.repository

import com.example.prak8.Data.dao.MahasiswaDao
import com.example.prak8.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> {
        return mahasiswaDao.getAllMahasiswa()
    }
    override fun getMhs(nim: String): Flow<Mahasiswa> {
        return mahasiswaDao.getMahasiswa(nim)
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.deleteMahasiswa(mahasiswa)
    }







}