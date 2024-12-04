package com.example.banco_Blasco_Alejandro.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ItemMovementsBinding
import com.example.banco_Blasco_Alejandro.fragments.MovementsListener
import com.example.banco_Blasco_Alejandro.pojo.Movimiento

class MovementsAdapter(private var movimientos: List<Movimiento>, private val listener: MovementsListener): RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMovementsBinding.bind(view)
        fun setListener(movimiento: Movimiento){
            binding.root.setOnClickListener{
                listener.OnClickMovements(movimiento)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movements, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovementsAdapter.ViewHolder, position: Int) {
        val movimiento = movimientos[position]
        val datos = movimiento.getFechaOperacion().toString() + "Importe: " + movimiento.getImporte()
        with (holder){
            setListener(movimiento)
            binding.tvMovimientos.text = movimiento.getDescripcion()
            binding.tvDatosMovimientos.text = datos
        }
    }

    override fun getItemCount(): Int = movimientos.size



    fun updateData(movimientosNuevos: ArrayList<Movimiento>) {
        this.movimientos = movimientosNuevos
        notifyDataSetChanged() // Esto actualiza la vista del RecyclerView
    }

}