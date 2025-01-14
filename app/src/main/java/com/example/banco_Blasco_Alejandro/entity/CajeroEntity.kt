package com.example.banco_Blasco_Alejandro.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cajeros")
class CajeroEntity (
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo val direccion: String?,
    @ColumnInfo val latitud: Double,
    @ColumnInfo val longitud: Double,
    @ColumnInfo val zoom: String?
)