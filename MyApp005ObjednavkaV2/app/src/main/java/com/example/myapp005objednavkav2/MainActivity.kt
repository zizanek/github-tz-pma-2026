package com.example.myapp005objednavkav2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005objednavkav2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //enableEdgeToEdge()
        setContentView(binding.root)

        // Ošetření systémových lišt – použije se přímo binding.main nebo binding.root
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Zavoláme hned po startu, aby se zobrazila výchozí cena vybraného kola
        updatePrice()

        // Nastavení listeneru na změny pro RadioGroup (výběr kola)
        binding.rgBikes.setOnCheckedChangeListener { _, _ -> updatePrice()  }

        // Nastavení listeneru na změny pro Checkboxy (příplatkový výbava)
        binding.cbFork.setOnClickListener { updatePrice() }
        binding.cbSaddle.setOnClickListener { updatePrice() }
        binding.cbHandlebar.setOnClickListener { updatePrice() }
        



    }

    private fun updatePrice() {
        var totalPrice = 0;

        // 1. Zjištění základní ceny vybraného kola
        totalPrice += when (binding.rgBikes.checkedRadioButtonId) {
            binding.rbBike1.id -> 135000
            binding.rbBike2.id -> 115000
            binding.rbBike3.id -> 95000
            else -> 0
        }

        // 2. Přičtení přípatkové výbavy
        if(binding.cbFork.isChecked) totalPrice += 5000
        if(binding.cbSaddle.isChecked) totalPrice += 1500
        if(binding.cbHandlebar.isChecked) totalPrice += 2500

        // 3. Zobrazení výsledku v barevném textview
        binding.tvTotalPrice.text = "Celkem: $totalPrice Kč"

    }
}