package com.invincible.diabolictale

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {
    private const val FIRST_TIME = "first_time"

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(FIRST_TIME, Context.MODE_PRIVATE)
    }

    var firstTime: Boolean
        get() = sharedPreferences.getBoolean(FIRST_TIME, true)
        set(value) {
            sharedPreferences.edit().putBoolean(FIRST_TIME, value).apply()
        }
}