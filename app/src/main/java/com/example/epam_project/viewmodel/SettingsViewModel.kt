package com.example.epam_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epam_project.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _darkMode = MutableLiveData<Boolean>()
    val darkMode: LiveData<Boolean> = _darkMode

    fun loadDarkMode() {
        viewModelScope.launch {
            repository.isDarkMode().collectLatest {
                _darkMode.value = it
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        repository.setDarkMode(enabled)
        _darkMode.value = enabled
    }
}
