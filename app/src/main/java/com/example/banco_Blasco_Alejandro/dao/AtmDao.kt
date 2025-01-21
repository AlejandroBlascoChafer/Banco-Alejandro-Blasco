package com.example.banco_Blasco_Alejandro.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.banco_Blasco_Alejandro.entity.AtmEntity

@Dao
interface AtmDao {

    @Query("SELECT * FROM atm")
    fun getAllAtm() : MutableList<AtmEntity>

    @Insert
    fun insertAll(cajeroEntityList : List<AtmEntity>)

    @Insert
    fun addAtm(cajeroEntity: AtmEntity)

    @Update
    fun updateAtm(cajeroEntity: AtmEntity)

    @Delete
    fun deleteAtm(cajeroEntity: AtmEntity)
}