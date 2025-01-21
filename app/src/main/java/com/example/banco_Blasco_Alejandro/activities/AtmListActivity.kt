package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.AtmApplication
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.adapters.AtmEntityAdapter
import com.example.banco_Blasco_Alejandro.adapters.AtmListListener
import com.example.banco_Blasco_Alejandro.databinding.ActivityAtmListBinding
import com.example.banco_Blasco_Alejandro.entity.AtmEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtmListActivity : AppCompatActivity(), AtmListListener {

    private lateinit var cajeroAdapter: AtmEntityAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityAtmListBinding

    private lateinit var atmEntityList: List<AtmEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar: androidx.appcompat.widget.Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Banco"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_salir)

        GlobalScope.launch(Dispatchers.Main) {
            val atmEntityList = withContext(Dispatchers.IO) {
                AtmApplication.database.atmDao().getAllAtm()
            }
            cajeroAdapter = AtmEntityAdapter(atmEntityList, this@AtmListActivity)
            linearLayoutManager = LinearLayoutManager(this@AtmListActivity)

            binding.rvCajeros.apply {
                layoutManager = linearLayoutManager
                adapter = cajeroAdapter
            }
        }






        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClickCajero(cajero: AtmEntity) {
        startActivity(Intent(this, AtmFormActivity::class.java).putExtra("Atm", cajero))
    }
}