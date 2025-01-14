package com.example.banco_Blasco_Alejandro.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.banco_Blasco_Alejandro.entity.CajeroEntity

@Dao
interface CajeroDao {

    @Query("SELECT * FROM cajeros")
    fun getAllCajeros() : MutableList<CajeroEntity>

    @Query("INSERT INTO cajeros")
    fun insertAll(cajeroEntityList : List<CajeroEntity>)

    @Insert
    fun addCajero(cajeroEntity: CajeroEntity)

    @Update
    fun updateCajero(cajeroEntity: CajeroEntity)

    @Delete
    fun deleteCajero(cajeroEntity: CajeroEntity)
}