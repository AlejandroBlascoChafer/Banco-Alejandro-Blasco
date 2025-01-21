package com.example.banco_Blasco_Alejandro

import android.app.Application
import androidx.room.Room
import com.example.banco_Blasco_Alejandro.bd.AtmDatabase

class AtmApplication : Application() {
    companion object{
        lateinit var database: AtmDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            AtmDatabase::class.java,
            "AtmDatabase")
            .build()
    }
}