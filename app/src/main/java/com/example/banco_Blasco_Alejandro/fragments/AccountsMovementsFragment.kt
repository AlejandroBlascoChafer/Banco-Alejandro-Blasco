package com.example.banco_Blasco_Alejandro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.activities.GlobalPositionDetailsActivity
import com.example.banco_Blasco_Alejandro.adapters.GlobalPositionAdapter
import com.example.banco_Blasco_Alejandro.adapters.MovementsAdapter
import com.example.banco_Blasco_Alejandro.adapters.OnClickListener
import com.example.banco_Blasco_Alejandro.databinding.DialogMovementBinding
import com.example.banco_Blasco_Alejandro.databinding.FragmentAccountsMovementsBinding
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.pojo.Cuenta
import com.example.banco_Blasco_Alejandro.pojo.Movimiento
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AccountsMovementsFragment : Fragment(), MovementsListener {

    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var bindingDialog: DialogMovementBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)

        val cuenta = arguments?.getSerializable("cuenta") as? Cuenta
        var movimientos:List<Movimiento>

        if (cuenta != null){
            movimientos = getMovimientos(cuenta)

            movementsAdapter = MovementsAdapter(movimientos, this)
            linearLayoutManager = LinearLayoutManager(context)

            binding.rvMovimientos.apply {
                layoutManager = linearLayoutManager
                adapter = movementsAdapter
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            it.isChecked = true

            when (it.itemId) {
                R.id.nav_todos -> {
                    if (cuenta != null){
                        movimientos = getMovimientos(cuenta)
                        movementsAdapter = MovementsAdapter(movimientos, this)
                        binding.rvMovimientos.apply {
                            layoutManager = linearLayoutManager
                            adapter = movementsAdapter
                        }
                    }
                }
                R.id.nav_tipo_0 -> {
                    if (cuenta != null){
                        movimientos = getMovimientosTipo(cuenta, 0)
                        movementsAdapter = MovementsAdapter(movimientos, this)
                        binding.rvMovimientos.apply {
                            layoutManager = linearLayoutManager
                            adapter = movementsAdapter
                        }
                    }
                }
                R.id.nav_tipo_1 -> {
                    if (cuenta != null){
                        movimientos = getMovimientosTipo(cuenta, 1)
                        movementsAdapter = MovementsAdapter(movimientos, this)
                        binding.rvMovimientos.apply {
                            layoutManager = linearLayoutManager
                            adapter = movementsAdapter
                        }
                    }
                }
                R.id.nav_tipo_2 -> {
                    if (cuenta != null){
                        movimientos = getMovimientosTipo(cuenta, 2)
                        movementsAdapter = MovementsAdapter(movimientos, this)
                        binding.rvMovimientos.apply {
                            layoutManager = linearLayoutManager
                            adapter = movementsAdapter
                        }
                    }
                }
            }
            false

        }

        return binding.root
    }

    private fun getMovimientos(cuenta: Cuenta): List<Movimiento> {
        val mbo = MiBancoOperacional.getInstance(requireContext())
        return mbo?.getMovimientos(cuenta) as? ArrayList<Movimiento> ?: listOf()
    }
    private fun getMovimientosTipo(cuenta: Cuenta, tipo: Int): List<Movimiento> {
        val mbo = MiBancoOperacional.getInstance(requireContext())
        return mbo?.getMovimientosTipo(cuenta, tipo) as? ArrayList<Movimiento> ?: listOf()
    }


    override fun OnClickMovements(movimiento: Movimiento) {
        bindingDialog = DialogMovementBinding.inflate(layoutInflater)
        val id = "ID: ${movimiento.getId()}"
        val tipo = "Tipo: ${movimiento.getTipo()}"
        val fechaOperacion = "FechaOperación: ${movimiento.getFechaOperacion()}"
        val descripcion = "Descripción: ${movimiento.getDescripcion()}"
        val importe = "Importe: ${movimiento.getImporte()}"
        val cuentaOrigen = "CuentaOrigen: ${movimiento.getCuentaOrigen()}"
        val cuentaDestino = "CuentaDestino: ${movimiento.getCuentaDestino()}"

        bindingDialog.tvID.text = id
        bindingDialog.tvTipo.text = tipo
        bindingDialog.tvFechaOperacion.text = fechaOperacion
        bindingDialog.tvDescripcion.text = descripcion
        bindingDialog.tvImporte.text = importe
        bindingDialog.tvOrigen.text = cuentaOrigen
        bindingDialog.tvDestino.text = cuentaDestino



        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Detalles del movimiento")
            .setView(bindingDialog.root)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.cancel()
            }
            .setCancelable(false)
            .show()

    }



}