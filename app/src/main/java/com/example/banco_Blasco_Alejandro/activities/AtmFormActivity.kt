package com.example.banco_Blasco_Alejandro.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.AtmApplication
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.adapters.AtmEntityAdapter
import com.example.banco_Blasco_Alejandro.databinding.ActivityAtmFormBinding
import com.example.banco_Blasco_Alejandro.databinding.DialogDeleteAtmBinding
import com.example.banco_Blasco_Alejandro.entity.AtmEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtmFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtmFormBinding
    private lateinit var bindingDialog: DialogDeleteAtmBinding
    private lateinit var atm: AtmEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar: androidx.appcompat.widget.Toolbar = binding.appbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(true)


        atm = intent.getSerializableExtra("Atm") as AtmEntity
        binding.etDireccion.hint = atm.direccion
        binding.etLatitud.hint = atm.latitud.toString()
        binding.etLongitud.hint = atm.longitud.toString()



        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.gestion_atm_menu, menu)
        return true
    }

    // Manejar las acciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_borrar -> {
                bindingDialog = DialogDeleteAtmBinding.inflate(layoutInflater)
                MaterialAlertDialogBuilder(this)
                    .setView(bindingDialog.root)
                    .setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss()}
                    .setPositiveButton("OK") { dialog, _ ->
                        deleteAtm(atm)
                        dialog.dismiss()
                    }
                    .create().show()
                true
            }
            R.id.nav_actualizar -> {
                Toast.makeText(this, "Actualizar", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAtm(atm: AtmEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                AtmApplication.database.atmDao().deleteAtm(atm)
            }
        }
        startActivity(Intent(this, AtmManagementActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
        finish()

    }
}