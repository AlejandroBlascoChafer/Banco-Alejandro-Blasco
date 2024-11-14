package com.example.banco_Blasco_Alejandro.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ItemPosicionGlobalBinding
import com.example.banco_Blasco_Alejandro.pojo.Cuenta

class PosicionAdapter(private val cuentas: ArrayList<Cuenta>): RecyclerView.Adapter<PosicionAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPosicionGlobalBinding.bind(view)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosicionAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_posicion_global, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosicionAdapter.ViewHolder, position: Int) {
        val posicion = cuentas.get(position)
        with(holder){
            if (posicion.getSaldoActual()!! > 0) {
                binding.tvSaldo.setTextColor(Color.GREEN)
            } else {
                binding.tvSaldo.setTextColor(Color.RED)
            }
            binding.tvCuenta.text = posicion.getNumeroCuenta()
            binding.tvSaldo.text = posicion.getSaldoActual().toString()
        }
    }

    override fun getItemCount(): Int = cuentas.size
}