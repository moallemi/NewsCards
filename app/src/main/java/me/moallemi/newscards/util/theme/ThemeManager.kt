package me.moallemi.newscards.util.theme

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

class ThemeManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        const val key: String = "news_theme"
    }

    enum class ThemePreference(val themeName: String) {
        LIGHT_MODE("Light"),
        DARK_MODE("Dark"),
        AUTO_BATTERY_MODE("Auto-battery"),
        FOLLOW_SYSTEM_MODE("System")
    }

    fun initTheme() {
        val theme = getCurrentTheme()
        applyTheme(theme)
    }

    fun getCurrentTheme(): ThemePreference {
        return when (preferences.getString(key, "")) {
            ThemePreference.LIGHT_MODE.themeName -> ThemePreference.LIGHT_MODE
            ThemePreference.DARK_MODE.themeName -> ThemePreference.DARK_MODE
            ThemePreference.FOLLOW_SYSTEM_MODE.themeName -> ThemePreference.FOLLOW_SYSTEM_MODE
            else -> ThemePreference.FOLLOW_SYSTEM_MODE
        }
    }

    private fun applyTheme(themePreference: ThemePreference) {
        when (themePreference) {
            ThemePreference.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemePreference.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ThemePreference.AUTO_BATTERY_MODE -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            )
            ThemePreference.FOLLOW_SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
    }

    fun changeTheme() {
        when (getCurrentTheme()) {
            ThemePreference.FOLLOW_SYSTEM_MODE -> changeTheme(ThemePreference.DARK_MODE)
            ThemePreference.DARK_MODE -> changeTheme(ThemePreference.LIGHT_MODE)
            ThemePreference.LIGHT_MODE -> changeTheme(ThemePreference.DARK_MODE)
            ThemePreference.AUTO_BATTERY_MODE -> changeTheme(ThemePreference.AUTO_BATTERY_MODE)
        }
    }

    private fun changeTheme(theme: ThemePreference) {
        when (theme) {
            ThemePreference.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemePreference.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ThemePreference.AUTO_BATTERY_MODE -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            )
            ThemePreference.FOLLOW_SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
        preferences.edit().putString(key, theme.themeName).apply()
    }
}