package com.example.pertemuan10.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pertemuan10.data.entity.Mahasiswa
import java.util.concurrent.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMhs() : Flow<List<Mahasiswa>> //methode untuk memanggil fungsi untuk mendapatkan semua data

    fun getMhs(nim: String) : Flow<Mahasiswa> //mengambil data mahasiswa berdasarkan Nim

    suspend fun deleteMhs(mahasiswa: Mahasiswa) // menghapus data mahasiswa

    suspend fun updateMhs(mahasiswa: Mahasiswa) //memperbarui data mahasiswa di dalam database

}
