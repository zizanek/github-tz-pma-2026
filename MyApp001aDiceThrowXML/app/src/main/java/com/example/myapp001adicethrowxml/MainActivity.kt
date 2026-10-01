package com.example.myapp001adicethrowxml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val diceSymbols = listOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")
        val tvDice = findViewById<TextView>(R.id.tvDice)
        val btnRoll = findViewById<Button>(R.id.btnRoll)

        btnRoll.setOnClickListener {
            lifecycleScope.launch {
                btnRoll.isEnabled = false
                repeat(10) {
                    tvDice.text = diceSymbols.random()
                    delay(250)
                }

                val diceValue = (1..6).random()
                tvDice.text = diceSymbols[diceValue - 1]
                btnRoll.isEnabled = true
            }
        }









    }
}