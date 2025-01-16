package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ActivityCajeroManagementBinding

class CajeroManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCajeroManagementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCajeroManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolBar: androidx.appcompat.widget.Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Banco"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_salir)


        binding.btnListaCajeros.setOnClickListener{
            startActivity(Intent(this, CajeroListActivity::class.java))
        }


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Finaliza la actividad y regresa a la anterior
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }








}