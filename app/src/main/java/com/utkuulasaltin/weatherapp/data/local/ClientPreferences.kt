package com.utkuulasaltin.weatherapp.data.local

import android.content.Context

class ClientPreferences(context: Context) {
    private val PREFS_FILE_NAME = "client_preferences"
    private val prefs = context.getSharedPreferences(PREFS_FILE_NAME, 0)

    companion object {
        const val API_KEY = "API_KEY"
    }

    fun setApiKey(key: String) {
        prefs.edit().putString(API_KEY, key).apply()
    }

    fun getApiKey(): String? {
        return prefs.getString(API_KEY, "8ddadecc7ae4f56fee73b2b405a63659")
    }
}