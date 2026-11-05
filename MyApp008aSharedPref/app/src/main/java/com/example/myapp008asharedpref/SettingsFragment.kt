package com.example.myapp008asharedpref

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapp008asharedpref.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Získání SharedPreferences (Vytvoří "trezor" s názvem GameProfile)
        val sharedPref = requireActivity().getSharedPreferences("GameProfile", Context.MODE_PRIVATE)

        // 2. Načtení uložených dat ihned po otevření obrazovky (výchozí hodnoty jsou prázdné)
        val savedName = sharedPref.getString("NICKNAME", "")
        val isHardcore = sharedPref.getBoolean("HARDCORE", false)

        // Propsaní načtených dat do UI
        binding.etNickname.setText(savedName)
        binding.switchHardcoreMode.isChecked = isHardcore

        // 3. Reakce na kliknutí tlačítka Uložit
        binding.btnSave.setOnClickListener {
            val currentName = binding.etNickname.text.toString()
            val currentHardcore = binding.switchHardcoreMode.isChecked

            // Příprava dat k zápisu
            val editor = sharedPref.edit()
            editor.putString("NICKNAME", currentName)
            editor.putBoolean("HARDCORE", currentHardcore)

            // Uspání vlákna
            Thread.sleep(5000)

            // Skutečný synchronní zápis
            editor.commit()

            // Zobrazení Toast notifikace (jednoduchá bublina, na kterou nejde kliknout)
            Toast.makeText(requireContext(), "Uloženo pomoccí Shared Preferences principu", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}