package com.example.myapp008bdatastore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapp008bdatastore.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// 1. Vytvoření instance DataStore na úrovni souboru (tzv. top-level)
val Context.dataStore by preferencesDataStore(name = "gamer_profile")

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    // 2. Definice klíčů (DataStore je na rozdíl od SharedPreferences typově bezpečný)
    private val NICKNAME_KEY = stringPreferencesKey("NICKNAME")
    private val HARDCORE_KEY = booleanPreferencesKey("HARDCORE")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Načtení dat (Asynchronně přes Coroutines a Flow)
        viewLifecycleOwner.lifecycleScope.launch {
            val preferences = requireContext().dataStore.data.first()
            binding.etNickname.setText(preferences[NICKNAME_KEY] ?: "")
            binding.switchHardcoreMode.isChecked = preferences[HARDCORE_KEY] ?: false
        }

        // 4. Reakce na kliknutí tlačítka Uložit
        binding.btnSave.setOnClickListener {
            val currentName = binding.etNickname.text.toString()
            val currentHardcore = binding.switchHardcoreMode.isChecked

            // Spuštění asynchronní práce na pozadí
            viewLifecycleOwner.lifecycleScope.launch {
                // A) UX: Zobrazíme kolečko
                binding.progressBar.visibility = View.VISIBLE
                binding.btnSave.isEnabled = false // Zamezíme dvojitému kliknutí

                // B) Simulace těžké I/O operace na pomalém disku
                // Tímto odložíme práci na 3 vteřiny, ale HLAVNÍ VLÁKNO ZŮSTÁVÁ VOLNÉ!
                delay(3000)

                // C) Samotný asynchronní zápis do DataStore
                requireContext().dataStore.edit { prefs ->
                    prefs[NICKNAME_KEY] = currentName
                    prefs[HARDCORE_KEY] = currentHardcore
                }

                // D) UX: Skryjeme kolečko a zapneme zpět tlačítko
                binding.progressBar.visibility = View.INVISIBLE
                binding.btnSave.isEnabled = true

                // E) Zobrazení Snackbaru s interaktivním tlačítkem
                Snackbar.make(binding.root, "Uloženo moderní cestou", Snackbar.LENGTH_LONG)
                    .setAction("Zpět") {
                        // Ukázka interakce - zde by se v reálu přepsala data, my si pro ukázku zobrazíme Toast
                        Toast.makeText(requireContext(), "Akce vrácena zpět!", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }
        }





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}