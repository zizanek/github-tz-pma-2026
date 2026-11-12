package com.example.myapp009amynotehub

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp009amynotehub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Deklarace bindingu na úrovni třídy, aby byl přístupný i v jiných metodách
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        // 2. Inicializace (nafouknutí) layoutu přes ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Zobrazení kořenového elementu (místo původního R.layout.activity_main)
        setContentView(binding.root)

        // 4. Využití bindingu i pro EdgeToEdge insets (místo findViewById(R.id.main) použijeme binding.main)
        /*ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Po kliknutí na FAB otevře AddNoteActivity
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }
}