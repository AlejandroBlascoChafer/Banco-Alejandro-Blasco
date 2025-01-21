package com.example.banco_Blasco_Alejandro.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banco_Blasco_Alejandro.dao.AtmDao
import com.example.banco_Blasco_Alejandro.entity.AtmEntity


@Database(entities = [AtmEntity::class], version = 1)
abstract class AtmDatabase : RoomDatabase() {
    abstract fun atmDao(): AtmDao
}