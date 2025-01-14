package com.example.banco_Blasco_Alejandro.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banco_Blasco_Alejandro.dao.CajeroDao
import com.example.banco_Blasco_Alejandro.entity.CajeroEntity

@Database(entities = [CajeroEntity::class], version = 1)
abstract class CajeroDatabase : RoomDatabase() {
    abstract fun cajeroDao(): CajeroDao
}