package com.example.banco_Blasco_Alejandro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ItemAtmBinding
import com.example.banco_Blasco_Alejandro.entity.AtmEntity

class AtmEntityAdapter(private var atms: List<AtmEntity>, private val listener: AtmListListener):
    RecyclerView.Adapter<AtmEntityAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemAtmBinding.bind(view)

        fun setListener(cajeros: AtmEntity) {
            binding.root.setOnClickListener{
                listener.onClickCajero(cajeros)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = atms.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = atms.get(position)
        with(holder){
            setListener(cajero)
            val texto = "Cajero ${cajero.id}"
            binding.tvNumeroCajero.text = texto
            binding.tvDireccion.text = cajero.direccion
        }
    }
    fun updateList(nuevaLista: List<AtmEntity>) {
        atms = nuevaLista
        notifyDataSetChanged()
    }

}