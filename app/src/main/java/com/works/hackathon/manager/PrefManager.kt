package com.works.hackathon.manager

import android.content.Context
import android.preference.PreferenceManager

class PrefManager(context : Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveData(email : String, password : String) {
        val editor = sharedPreferences.edit()
        editor.putString(EMAIL_KEY,email)
        editor.putString(PASSWORD_KEY,password)
        editor.apply()
    }

    fun getEmail() : String? {
        return sharedPreferences.getString(EMAIL_KEY, null)
    }

    fun clearData() {
        val editor = sharedPreferences.edit()
        editor.remove(PASSWORD_KEY)
        editor.remove(EMAIL_KEY)
        editor.apply()
    }

    companion object {
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
    }

}