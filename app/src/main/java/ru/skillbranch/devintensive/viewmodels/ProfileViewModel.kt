package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository
import ru.skillbranch.devintensive.utils.Utils

class ProfileViewModel : ViewModel() {

    private val repository = PreferencesRepository
    private val profileData = mutableLiveData(repository.getProfile())
    private val appTheme = mutableLiveData(repository.theme)
    private val repositoryError = mutableLiveData(false)

    fun getProfileData(): LiveData<Profile> = profileData
    fun getTheme(): LiveData<Int> = appTheme
    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = !Utils.isRepositoryValid(repository)
    }

    fun saveProfileData(profile: Profile) {
        val data = if (repositoryError.value!!) profile.copy(repository = "") else profile
        repository.saveProfile(data)
        profileData.value = data
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.theme = appTheme.value!!
    }
}
