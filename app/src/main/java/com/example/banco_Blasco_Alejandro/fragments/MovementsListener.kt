package com.example.banco_Blasco_Alejandro.fragments

import com.example.banco_Blasco_Alejandro.pojo.Movimiento

interface MovementsListener {
    fun OnClickMovements(movimiento: Movimiento)
}