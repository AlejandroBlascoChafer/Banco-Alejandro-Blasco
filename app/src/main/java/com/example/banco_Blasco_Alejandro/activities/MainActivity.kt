package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R

import com.example.banco_Blasco_Alejandro.databinding.ActivityMainBinding
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar: androidx.appcompat.widget.Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)


        binding.navigationView?.setNavigationItemSelectedListener(this)

        // Configuración del DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.appbar,
            R.string.open_nav, R.string.close_nav
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        val bienvenida = "Bienvenido/a \n${cliente.getNombre()}"
        binding.idTextView.text = bienvenida

        binding.btnSalir.setOnClickListener{
            navigateToWelcome()
        }

        binding.btnCambiarContrasenya.setOnClickListener{
            navigateToPassword(cliente)
        }

        binding.btnTransferencias.setOnClickListener{
            navigateToTransfer(cliente)
        }

        binding.btnPosicionGlobal.setOnClickListener{
            navigateToGlobalPosition(cliente)
        }

        binding.btnMovimientos.setOnClickListener{
            navigateToMovements(cliente)
        }

        binding.btnCajeros.setOnClickListener{
            navigateToAtms()
        }

                enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun navigateToGlobalPosition(cliente: Cliente) {
        val intent = Intent(this, GlobalPositionActivity::class.java)
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }

    private fun navigateToMovements(cliente: Cliente) {
        val intent = Intent(this, MovementsActivity::class.java)
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }

    private fun navigateToTransfer(cliente: Cliente) {
        val intent = Intent(this, TransfersActivity::class.java)
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }

    private fun navigateToPassword(cliente: Cliente) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }

    private fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAtms() {
        val intent = Intent(this, CajeroManagementActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        when (item.itemId) {
            R.id.nav_global_position -> navigateToGlobalPosition(cliente)
            R.id.nav_movements -> navigateToMovements(cliente)
            R.id.nav_transfers -> navigateToTransfer(cliente)
            R.id.nav_change_password -> navigateToPassword(cliente)
            R.id.nav_promotions -> {
                // Lógica para promociones
            }
            R.id.nav_atms -> {
                navigateToAtms()
            }
            R.id.nav_sett -> navigateToSettings()
            R.id.nav_exit -> navigateToWelcome()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}