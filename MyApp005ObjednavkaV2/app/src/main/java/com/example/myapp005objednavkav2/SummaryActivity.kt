package com.example.myapp005objednavkav2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005objednavkav2.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()

        // Inicializace ViewBindingu
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ochrana před překryvem systémovými lištami
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Přidá vnitřní okraj (padding) odpovídající velikosti horní a spodní systémové lišty
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Příjmutí dat z intentu
        val bikeName = intent.getStringExtra("EXTRA_BIKE_NAME") ?: "Neznámé kolo"
        val totalPrice = intent.getStringExtra("EXTRA_TOTAL_PRICE") ?: "Celkem: 0 Kč"

        val hasFork = intent.getBooleanExtra("EXTRA_HAS_FORK", false)
        val hasSaddle = intent.getBooleanExtra("EXTRA_HAS_SADDLE", false)
        val hasHandlebar = intent.getBooleanExtra("EXTRA_HAS_HANDLEBAR", false)

        // Sestavení textu pro příplatkovou výbavu
        var extrasText = "Příplatková výbava:\n"
        var hasAnyExtra = false

        if(hasFork) {
            extrasText += "- Lepší vidlice\n"
            hasAnyExtra = true
        }

        if(hasSaddle) {
            extrasText += "- Lepší sedlo\n"
            hasAnyExtra = true
        }

        if(hasHandlebar) {
            extrasText += "- Lepší říditka\n"
            hasAnyExtra = true
        }

        // Pokud uživatel nezaškrtl nic
        if(!hasAnyExtra) {
            extrasText += "- žádná"
        }

        // Zobrazení dat v textových polích
        binding.tvSelectedBike.text = "Vybrané kolo: $bikeName"
        binding.tvExtras.text = extrasText
        binding.tvFinalPrice.text = totalPrice

        // Obsluha tlačítka Zpět pro návrat na úpravu objednávky
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}