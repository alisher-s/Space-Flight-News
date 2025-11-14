package com.example.epam_project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.epam_project.databinding.ActivitySettingsBinding
import com.example.epam_project.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.darkMode.observe(this) { isDark ->
            binding.switchDarkMode.isChecked = isDark
            val mode =
                if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        viewModel.loadDarkMode()

        binding.switchDarkMode.setOnCheckedChangeListener { _, checked ->
            viewModel.toggleDarkMode(checked)
        }
    }
}
