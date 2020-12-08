package me.moallemi.newscards.extension

import me.moallemi.newscards.R
import me.moallemi.newscards.util.theme.ThemeManager

fun ThemeManager.ThemePreference.getIcon(): Int {
    return when (this) {
        ThemeManager.ThemePreference.LIGHT_MODE -> R.drawable.ic_day
        ThemeManager.ThemePreference.DARK_MODE -> R.drawable.ic_night
        ThemeManager.ThemePreference.AUTO_BATTERY_MODE -> R.drawable.ic_auto
        ThemeManager.ThemePreference.FOLLOW_SYSTEM_MODE -> R.drawable.ic_follow_system
    }
}