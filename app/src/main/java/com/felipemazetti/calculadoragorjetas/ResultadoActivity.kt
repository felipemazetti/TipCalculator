package com.felipemazetti.calculadoragorjetas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtContaFl = intent.getFloatExtra(KEY_EDT_CONTA, 0.0f)
        val edtPessoasFl = intent.getIntExtra(KEY_EDT_PESSOAS, 0)
        val percentualGorjeta = intent.getFloatExtra(KEY_P_GORJETA, 0.0f)

        val conversaoGorjeta = percentualGorjeta * 100
        val percentualGorjetaInt = conversaoGorjeta.toInt()
        val percentualGorjetaSt = percentualGorjetaInt.toString()

        val edtContaFormatada = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(edtContaFl)
        val edtContaSt = edtContaFormatada.toString()

        val valorGorjeta = edtContaFl * percentualGorjeta
        val totalComGorjeta = valorGorjeta + edtContaFl
        val totalComGorjetaFormatada = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(totalComGorjeta)


        val valorPorPessoa = totalComGorjeta / edtPessoasFl
        val valorPorPessoaSt = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(valorPorPessoa)


        val dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm").format(System.currentTimeMillis())

        val tvDateTime = findViewById<TextView>(R.id.tv_data_hora)
        tvDateTime.text = dateTime

        val tvTotalConta = findViewById<TextView>(R.id.tv_total_conta)
        tvTotalConta.text = "Total da Conta: "+totalComGorjetaFormatada

        val tvTotalSemGorjeta = findViewById<TextView>(R.id.tv_total_sem_gorjeta)
        tvTotalSemGorjeta.text = "Total sem Gorjeta: "+edtContaSt

        val tvPorcentagem = findViewById<TextView>(R.id.tv_gorjeta)
        tvPorcentagem.text = "Gorjeta: "+percentualGorjetaSt+"%"

        val tvTotalPessoa = findViewById<TextView>(R.id.tv_total_por_pessoa)
        tvTotalPessoa.text = "Total por pessoa: "+valorPorPessoaSt

        val btnInicio = findViewById<Button>(R.id.btnNovoCalculo)
        btnInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }







    }
}