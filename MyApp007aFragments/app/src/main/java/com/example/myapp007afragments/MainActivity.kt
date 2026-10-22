package com.example.myapp007afragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.myapp007afragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // (Volitelné) Zobrazení výchozího fragmentu po spuštění aplikace
        // Pokud toto vynecháte, bude kontejner po zapnutí aplikace prázdný
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentA())
                .commit()
        }

        // Nastavení fragmentu po kliknutí na první tlačítko
        binding.btnFragmentA.setOnClickListener {
            supportFragmentManager.beginTransaction()
                // Vymění obsah kontejneru za Fragment A
                .replace(R.id.fragmentContainer, FragmentA())
                // Potvrzení a provedení změny
                .commit()
        }

        // Nastavení fragmentu B po kliknutí na druhé tlačítko
        binding.btnFragmentB.setOnClickListener {
            supportFragmentManager.beginTransaction()
                // Vymění obsah kontejneru za Fragment B
                .replace(R.id.fragmentContainer, FragmentB())
                // Potvrzení a provedení změny
                .commit()
        }

    }
}