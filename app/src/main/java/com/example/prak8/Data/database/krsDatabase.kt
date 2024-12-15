package com.example.prak8.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prak8.Data.dao.MahasiswaDao
import com.example.prak8.Data.entity.Mahasiswa


// mendifiniskan db denan tbal mhs
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class krsDatabase: RoomDatabase() {        // mengambil library dari db

    // mendifinisikan fungsi untuk mengakses data mhs
    abstract fun mahasiswaDao(): MahasiswaDao       // fungsinya ngambil dari dta class mhs

    companion object{
        @Volatile //memastikan bahwa nilai variabel instance selau sama di semua thread
        private var Instance: krsDatabase? = null

        fun getDatabase(context: Context): krsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    krsDatabase::class.java,            // class databse
                    "krsDatabase"               // nama database
                )
                    .build().also { Instance = it }
            })
        }

    }
}