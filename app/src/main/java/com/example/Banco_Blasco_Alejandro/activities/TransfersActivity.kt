package com.example.Banco_Blasco_Alejandro.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Banco_Blasco_Alejandro.R
import com.example.Banco_Blasco_Alejandro.databinding.ActivityTransfersBinding

class TransfersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransfersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransfersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        //Spinner cuenta origen
        val cuentasOrigen = binding.root.context.resources.getStringArray(R.array.cuentas)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentasOrigen)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCuentaOrigen.adapter = adapter
        binding.spCuentaPropia.adapter = adapter


        //Radio button cuenta propia/cuenta ajena
        binding.rbCuentaPropia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                binding.spCuentaPropia.visibility = View.VISIBLE
                binding.etCuentaAjena.visibility = View.INVISIBLE
                binding.rbCuentaAjena.isChecked = false
            }
        }
        binding.rbCuentaAjena.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                binding.etCuentaAjena.visibility = View.VISIBLE
                binding.spCuentaPropia.visibility = View.INVISIBLE
                binding.rbCuentaPropia.isChecked = false
            }
        }


        //Spinner divisas
        val divisas = binding.root.context.resources.getStringArray(R.array.divisas)

        val adapterDivisas = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        adapterDivisas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spTipoMoneda.adapter = adapterDivisas



        //Boton cancelar
        binding.btnCancelar.setOnClickListener{
            binding.cbJustificante.isChecked = false
            binding.spCuentaPropia.setSelection(0)
            binding.spCuentaOrigen.setSelection(0)
            binding.etCantidad.setText("")
            binding.etCuentaAjena.setText("")
        }

        //Boton aceptar
        binding.btnEnviar.setOnClickListener{
            val cuentaOrigen = binding.spCuentaOrigen.selectedItem as String
            var cuentaDestino = ""
            var destino= ""

            if (binding.rbCuentaPropia.isChecked){
                destino = binding.rbCuentaPropia.text.toString()
                cuentaDestino = binding.spCuentaPropia.selectedItem as String
            } else if (binding.rbCuentaAjena.isChecked){
                destino = binding.rbCuentaAjena.text.toString()
                cuentaDestino = binding.etCuentaAjena.text.toString()
            }
            val importe = binding.etCantidad.text

            val divisa = binding.spTipoMoneda.selectedItem as String

            val justificante:String
            if (binding.cbJustificante.isChecked) {
                justificante = "Enviar justificante"
            } else {
                justificante = "No enviar justificante"
            }

            Toast.makeText(this@TransfersActivity, "Cuenta origen:\n $cuentaOrigen\n" +
                    "A $destino:\n $cuentaDestino\n" +
                    "Importe:\n $importe$divisa\n" +
                    justificante, Toast.LENGTH_SHORT).show()
        }





    }
}