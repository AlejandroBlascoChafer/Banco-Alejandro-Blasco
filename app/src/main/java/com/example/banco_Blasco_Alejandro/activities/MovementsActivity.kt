package com.example.banco_Blasco_Alejandro.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.adapters.CuentasSpinnerAdapter
import com.example.banco_Blasco_Alejandro.adapters.MovementsAdapter
import com.example.banco_Blasco_Alejandro.databinding.ActivityMovementsBinding
import com.example.banco_Blasco_Alejandro.fragments.MovementsListener
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.pojo.Cuenta
import com.example.banco_Blasco_Alejandro.pojo.Movimiento
import com.example.bancoapiprofe.bd.MiBancoOperacional

class MovementsActivity : AppCompatActivity(), MovementsListener {

    private lateinit var binding: ActivityMovementsBinding
    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        val cuentas = mbo?.getCuentas(cliente) as ArrayList<Cuenta>

        var movimientos = mbo.getMovimientos(cuentas[0])


        movementsAdapter = MovementsAdapter(movimientos as ArrayList<Movimiento>, this)

        linearLayoutManager = LinearLayoutManager(this)

        binding.rvMovimientos.apply {
            layoutManager = linearLayoutManager
            adapter = movementsAdapter
        }

        val spinnerAdapter = CuentasSpinnerAdapter(this, cuentas)


        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCuentas.adapter = spinnerAdapter





        binding.spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCuenta = cuentas[position]
                movimientos = mbo?.getMovimientos(selectedCuenta)

                // Actualizar el adaptador con los nuevos movimientos
                movementsAdapter.updateData(movimientos as ArrayList<Movimiento>)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }










        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun OnClickMovements(movimiento: Movimiento) {
        return
    }
}