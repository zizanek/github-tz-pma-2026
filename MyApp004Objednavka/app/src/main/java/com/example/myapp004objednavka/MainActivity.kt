package com.example.myapp004objednavka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp004objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Binding - deklarace binding objektu s odloženou inicializací
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()

        // 2. Binding - nafouknutí (inflate) layoutu do binding instance
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Nastavení kořenového pohledu (root) do okna aktivity
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)
        // Ošetření systémových lišt – použije se přímo binding.main nebo binding.root
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnOrder.setOnClickListener {
            val bike = when (binding.rgBikes.checkedRadioButtonId) {
                binding.rbBike1.id -> binding.rbBike1 // Pokud sedí ID prvního tlačítka, použij rbBike1
                binding.rbBike2.id -> binding.rbBike2
                binding.rbBike3.id -> binding.rbBike3

                else -> binding.rbBike1   // Záložní možnost (fallback), kdyby nebylo vybráno nic
            }

            val fork = binding.cbFork.isChecked
            val saddle = binding.cbSaddle.isChecked
            val handleBar = binding.cbHandleBar.isChecked

            val orderText = "Souhrn objednávky: " + "${bike.text}" +
                    (if(fork) "; lepší vidlice" else "") +
                    (if(saddle) "; lepší sedlo" else "") +
                    (if(handleBar) "; lepší říditka" else "")

            binding.tvOrder.text = orderText

            // Změna obrázku v závislosti na vybraném radiobuttonu

            binding.rbBike1.setOnClickListener {
                binding.ivBike.setImageResource(R.drawable.oiz_m10_tr)
            }

            binding.rbBike2.setOnClickListener {
                binding.ivBike.setImageResource(R.drawable.oiz_m20_tr)
            }

            binding.rbBike3.setOnClickListener {
                binding.ivBike.setImageResource(R.drawable.oiz_m30_tr)
            }

        }
        
    }
}