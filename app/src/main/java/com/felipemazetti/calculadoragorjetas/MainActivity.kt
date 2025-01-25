package com.felipemazetti.calculadoragorjetas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

const val KEY_EDT_CONTA = "KEY_EDT_CONTA"
const val KEY_EDT_PESSOAS = "KEY_EDT_PESSOAS"
const val KEY_P_GORJETA = "KEY_P_GORJETA"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val btnResultado = findViewById<Button>(R.id.btn_resultado)
        btnResultado.setOnClickListener {
            val edtContaInput = findViewById<TextInputEditText>(R.id.edt_conta)
            val edtConta = edtContaInput.text.toString()

            val rd10 = findViewById<RadioButton>(R.id.rbtn_10)
            val rd15 = findViewById<RadioButton>(R.id.rbtn_15)
            val rd20 = findViewById<RadioButton>(R.id.rbtn_20)
            val rd25 = findViewById<RadioButton>(R.id.rbtn_25)



            val edtPessoasInput = findViewById<TextInputEditText>(R.id.edt_pessoas)
            val edtPessoas = edtPessoasInput.text.toString().ifEmpty { "1" }

            if (edtConta.isEmpty()){

                edtContaInput.error = "Digite um valor"

            } else {
                val edtContaFl = edtConta.toFloat()
                val edtPessoasFl = edtPessoas.toInt()

                val percentualGorjeta = when{
                    rd10.isChecked -> 0.10f
                    rd15.isChecked -> 0.15f
                    rd20.isChecked -> 0.20f
                    rd25.isChecked -> 0.25f
                    else -> 0f
                }


                val intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra(KEY_EDT_CONTA, edtContaFl)
                intent.putExtra(KEY_EDT_PESSOAS, edtPessoasFl)
                intent.putExtra(KEY_P_GORJETA, percentualGorjeta)
                startActivity(intent)
            }

        }



    }
}