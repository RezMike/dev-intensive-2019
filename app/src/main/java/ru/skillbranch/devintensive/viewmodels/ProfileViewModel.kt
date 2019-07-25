package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {

    private val repository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()

    fun getProfileData(): LiveData<Profile> = profileData
    fun getTheme(): LiveData<Int> = appTheme
    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
        repositoryError.value = false
    }

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isRepoError(repository)
    }

    private fun isRepoError(repository: String): Boolean {
        var repo = repository
        if (repository.startsWith("https://")) {
            repo = repo.replace("https://", "")
        }
        if (repository.startsWith("www.")) {
            repo = repo.replace("www.", "")
        }

        if (!repository.startsWith("github.com/")) {
            return true
        }

        repo = repo.replace("github.com/", "")

        repoExcludeSet.forEach {
            if (repo.startsWith(it)) return true
        }

        return Regex("^[a-z0-9]+$").matches(repo)
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
        repository.saveAppTheme(appTheme.value!!)
    }
}

private val repoExcludeSet = setOf(
    "enterprise",
    "features",
    "topics",
    "collections",
    "trending",
    "events",
    "marketplace",
    "pricing",
    "nonprofit",
    "customer-stories",
    "security",
    "login",
    "join"
)
