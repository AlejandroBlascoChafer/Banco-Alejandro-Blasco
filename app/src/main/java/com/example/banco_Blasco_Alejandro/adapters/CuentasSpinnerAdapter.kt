package com.example.banco_Blasco_Alejandro.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.banco_Blasco_Alejandro.pojo.Cuenta

class CuentasSpinnerAdapter(context: Context, cuentas: List<Cuenta>) : ArrayAdapter<Cuenta>(context, android.R.layout.simple_spinner_item, cuentas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val cuenta = getItem(position)
        val numeroCuenta = cuenta?.getBanco() + "-" + cuenta?.getSucursal() +  "-" + cuenta?.getDc() + "-" +  cuenta?.getNumeroCuenta()
        (view as TextView).text = numeroCuenta
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val cuenta = getItem(position)
        val numeroCuenta = cuenta?.getNumeroCuenta() ?: "Sin número"
        (view as TextView).text = numeroCuenta
        return view
    }
}