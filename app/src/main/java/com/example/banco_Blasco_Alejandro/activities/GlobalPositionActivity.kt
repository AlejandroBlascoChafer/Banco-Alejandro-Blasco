package com.example.banco_Blasco_Alejandro.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.adapters.PosicionAdapter
import com.example.banco_Blasco_Alejandro.databinding.ActivityGlobalPositionBinding
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.pojo.Cuenta
import com.example.bancoapiprofe.bd.MiBancoOperacional

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var posicionAdapter: PosicionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente")
        val cuentas = mbo?.getCuentas(cliente as Cliente)

        posicionAdapter = PosicionAdapter(cuentas as ArrayList<Cuenta>)
        linearLayoutManager = LinearLayoutManager(this)

        binding.rvCuentas.apply {
            layoutManager = linearLayoutManager
            adapter = posicionAdapter
        }
















        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}