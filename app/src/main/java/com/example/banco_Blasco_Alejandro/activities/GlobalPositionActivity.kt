package com.example.banco_Blasco_Alejandro.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ActivityGlobalPositionBinding
import com.example.banco_Blasco_Alejandro.fragments.AccountsListener
import com.example.banco_Blasco_Alejandro.fragments.AccountFragment
import com.example.banco_Blasco_Alejandro.fragments.AccountsMovementsFragment
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), AccountsListener {


    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val clienteLogeado= intent.getSerializableExtra("Cliente")


        println("Cliente recibido en actividad: $clienteLogeado")

        if (savedInstanceState == null){
            val frgAccounts: AccountFragment = AccountFragment().apply{
                arguments = Bundle().apply {
                    putSerializable("cliente", clienteLogeado)
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frgCuentas, frgAccounts)
            transaction.commitNow()

            frgAccounts.setCuentasListener(this)

        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {

        val hayMovimiento = binding.frgContenedorMovimientos != null

        if (hayMovimiento){
            val frgAccountsMovements: AccountsMovementsFragment = AccountsMovementsFragment().apply{
                arguments = Bundle().apply {
                    putSerializable("cuenta", cuenta)
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frgContenedorMovimientos, frgAccountsMovements)
            transaction.commitNow()

        } else {
            val i = Intent(this, GlobalPositionDetailsActivity::class.java)
            i.putExtra("cuenta", cuenta)
            startActivity(i)
        }



    }
}