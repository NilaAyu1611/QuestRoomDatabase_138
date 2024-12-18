package com.example.prak8.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.prak8.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao                // data akses objek (jika kita mau menabhkan data pertama kali
interface MahasiswaDao {

    @Query("select* from mahasiswa")
    fun getAllMahasiswa() : Flow<List<Mahasiswa>>
    @Insert
    suspend fun insertMahasiswa(
        mahasiswa:Mahasiswa
    )
    @Query("SELECT * FROM mahasiswa WHERE nim= :nim")
    fun  getMahasiswa(nim: String): Flow<Mahasiswa>

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)







}
