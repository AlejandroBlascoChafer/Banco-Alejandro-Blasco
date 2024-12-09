package com.example.banco_Blasco_Alejandro.fragments

import com.example.banco_Blasco_Alejandro.pojo.Cuenta

interface AccountsListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}