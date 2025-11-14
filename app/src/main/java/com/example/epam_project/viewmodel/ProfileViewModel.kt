package com.example.epam_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epam_project.model.Profile
import com.example.epam_project.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    fun loadProfile() {
        viewModelScope.launch {
            repository.getProfile().collectLatest {
                _profile.value = it
            }
        }
    }
}
