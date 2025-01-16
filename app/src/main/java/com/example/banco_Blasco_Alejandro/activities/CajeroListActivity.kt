package com.example.banco_Blasco_Alejandro.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.adapters.CajeroEntityAdapter
import com.example.banco_Blasco_Alejandro.adapters.CajeroListListener
import com.example.banco_Blasco_Alejandro.databinding.ActivityCajeroListBinding
import com.example.banco_Blasco_Alejandro.entity.CajeroEntity
import com.example.banco_Blasco_Alejandro.entity.cajerosEntityLists

class CajeroListActivity : AppCompatActivity(), CajeroListListener {

    private lateinit var cajeroAdapter: CajeroEntityAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityCajeroListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCajeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar: androidx.appcompat.widget.Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Banco"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_salir)


        cajeroAdapter = CajeroEntityAdapter(cajerosEntityLists, this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.rvCajeros.apply {
            layoutManager = linearLayoutManager
            adapter = cajeroAdapter
        }






        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClickCajero(cajeros: CajeroEntity) {
        TODO("Not yet implemented")
    }
}