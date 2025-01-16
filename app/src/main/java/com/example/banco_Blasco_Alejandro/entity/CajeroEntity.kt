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
val cajerosEntityLists : List<CajeroEntity> = listOf(
    CajeroEntity(1, "Carrer del Clariano, 1, 46021 Valencia, Valencia, España",39.47600769999999, -0.3524475000000393, ""),
    CajeroEntity(2, "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España", 39.4710366, -0.3547525000000178, ""),
    CajeroEntity(3, "Av. del Port, 237, 46011 València, Valencia, España",39.46161999999999, -0.3376299999999901, ""),
    CajeroEntity(4, "Carrer del Batxiller, 6, 46010 València, Valencia, España",39.4826729, -0.3639118999999482, ""),
    CajeroEntity(5, "Av. del Regne de València, 2, 46005 València, Valencia, España",39.4647669, -0.3732760000000326, "")
)