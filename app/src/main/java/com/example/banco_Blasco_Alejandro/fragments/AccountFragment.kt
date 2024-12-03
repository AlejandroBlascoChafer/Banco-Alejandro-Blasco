package com.example.banco_Blasco_Alejandro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_Blasco_Alejandro.adapters.OnClickListener
import com.example.banco_Blasco_Alejandro.adapters.GlobalPositionAdapter
import com.example.banco_Blasco_Alejandro.databinding.FragmentAccountsBinding
import com.example.banco_Blasco_Alejandro.pojo.Cliente
import com.example.banco_Blasco_Alejandro.pojo.Cuenta
import com.example.bancoapiprofe.bd.MiBancoOperacional

private const val ARG_CLIENTE = "cliente"

class AccountFragment : Fragment(), OnClickListener {

    private lateinit var globalPositionAdapter: GlobalPositionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentAccountsBinding

    private lateinit var listener: AccountsListener

    private lateinit var cliente: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
            println("El fragment ha recibido el cliente")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val listaCuentas: ArrayList<Cuenta> = mbo?.getCuentas(cliente as Cliente?) as ArrayList<Cuenta>


        globalPositionAdapter = GlobalPositionAdapter(listaCuentas, this)
        linearLayoutManager = LinearLayoutManager(context)

        binding.rvCuentas.apply {
            layoutManager = linearLayoutManager
            adapter = globalPositionAdapter
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                }
            }
    }



    override fun onClick(cuenta: Cuenta) {
        if (::listener.isInitialized){
            listener.onCuentaSeleccionada(cuenta)
        }
    }
    fun setCuentasListener(listener: AccountsListener){
        this.listener = listener
    }
}