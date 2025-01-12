package com.example.banco_Blasco_Alejandro.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.banco_Blasco_Alejandro.R
import java.util.Locale

class SettingsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        val toolBar: androidx.appcompat.widget.Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Settings"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_salir)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val languagePreference = findPreference<ListPreference>("codigoidioma")
            languagePreference?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, newValue ->
                    val selectedLanguage = newValue as String
                    updateLanguage(selectedLanguage)
                    true // Devuelve true para guardar el valor en SharedPreferences
                }
        }

        private fun updateLanguage(languageCode: String) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)

            val config = resources.configuration
            config.setLocale(locale)

            // Actualiza la configuración
            requireActivity().baseContext.resources.updateConfiguration(
                config,
                requireActivity().baseContext.resources.displayMetrics
            )
            requireActivity().recreate()
        }
    }
}