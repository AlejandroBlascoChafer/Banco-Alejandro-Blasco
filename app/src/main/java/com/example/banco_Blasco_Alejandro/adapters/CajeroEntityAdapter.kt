package com.example.banco_Blasco_Alejandro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ItemAtmBinding
import com.example.banco_Blasco_Alejandro.entity.CajeroEntity

class CajeroEntityAdapter(private val cajeros: List<CajeroEntity>, private val listener: CajeroListListener):
    RecyclerView.Adapter<CajeroEntityAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemAtmBinding.bind(view)

        fun setListener(cajeros: CajeroEntity) {
            binding.root.setOnClickListener{
                listener.onClickCajero(cajeros)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cajeros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = cajeros.get(position)
        with(holder){
            setListener(cajero)
            binding.tvNumeroCajero.text = cajero.id.toString()
            binding.tvDireccion.text = cajero.direccion
        }
    }
}