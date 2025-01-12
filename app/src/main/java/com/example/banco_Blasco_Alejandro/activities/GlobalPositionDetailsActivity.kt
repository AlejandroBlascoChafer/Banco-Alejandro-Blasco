package com.example.banco_Blasco_Alejandro.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banco_Blasco_Alejandro.R
import com.example.banco_Blasco_Alejandro.databinding.ActivityGlobalPositionDetailsBinding
import com.example.banco_Blasco_Alejandro.databinding.FragmentAccountsBinding
import com.example.banco_Blasco_Alejandro.fragments.AccountFragment
import com.example.banco_Blasco_Alejandro.fragments.AccountsMovementsFragment

class GlobalPositionDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuenta = intent.getSerializableExtra("cuenta")

        if (savedInstanceState == null){
            val frgAccountsMovements: AccountsMovementsFragment = AccountsMovementsFragment().apply{
                arguments = Bundle().apply {
                    putSerializable("cuenta", cuenta)
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frgContenedorMovimientos, frgAccountsMovements)
            transaction.commitNow()





        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}