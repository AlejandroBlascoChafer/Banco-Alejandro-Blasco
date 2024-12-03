package com.example.banco_Blasco_Alejandro.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ActivityGlobalPositionBinding
import com.example.banco_Blasco_Alejandro.fragments.AccountsListener
import com.example.banco_Blasco_Alejandro.fragments.AccountFragment
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

        //Creamos la instancia del fragment
        if (clienteLogeado != null){
            println("Cliente recibido en actividad: $clienteLogeado")

            val frgAccounts: AccountFragment = AccountFragment.newInstance(clienteLogeado as Cliente)


            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frgCuentas, frgAccounts)
            transaction.commitNow()

            frgAccounts.setCuentasListener(this);
        } else {
            println("Error: Cliente no recibido en la actividad.")
        }

















        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        TODO("Not yet implemented")
    }
}