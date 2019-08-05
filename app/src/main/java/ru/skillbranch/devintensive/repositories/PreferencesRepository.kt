package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object PreferencesRepository {

    private const val APP_THEME = "APP_THEME"
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    var theme: Int by PrefDelegate(prefs, APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)

    var firstName: String by PrefDelegate(prefs, FIRST_NAME, "")
    var lastName: String by PrefDelegate(prefs, LAST_NAME, "")
    var about: String by PrefDelegate(prefs, ABOUT, "")
    var repository: String by PrefDelegate(prefs, REPOSITORY, "")
    var rating: Int by PrefDelegate(prefs, RATING, 0)
    var respect: Int by PrefDelegate(prefs, RESPECT, 0)

    fun getProfile(): Profile = Profile(firstName, lastName, about, repository, rating)

    fun saveProfile(profile: Profile) {
        firstName = profile.firstName
        lastName = profile.lastName
        about = profile.about
        repository = profile.repository
        rating = profile.rating
        respect = profile.respect
    }

    // for hometask 4
    fun getAppTheme() = theme
    fun saveAppTheme(theme: Int) {
        this.theme = theme
    }
}

@Suppress("UNCHECKED_CAST")
class PrefDelegate<TValue>(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defValue: TValue
) : ReadWriteProperty<Any?, TValue> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): TValue {
        with(preferences) {
            return when (defValue) {
                is Boolean -> (getBoolean(name, defValue) as? TValue) ?: defValue
                is Int -> (getInt(name, defValue) as TValue) ?: defValue
                is Float -> (getFloat(name, defValue) as TValue) ?: defValue
                is Long -> (getLong(name, defValue) as TValue) ?: defValue
                is String -> (getString(name, defValue) as TValue) ?: defValue
                else -> throw NotFoundRealizationException(defValue)
            }
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: TValue) {
        with(preferences.edit()) {
            when (value) {
                is Boolean -> putBoolean(name, value)
                is Int -> putInt(name, value)
                is Float -> putFloat(name, value)
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                else -> throw NotFoundRealizationException(value)
            }
            apply()
        }
    }

    class NotFoundRealizationException(defValue: Any?) : Exception("not found realization for $defValue")
}
