package com.android.kotlinmvvm.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class MyPreferences {
    companion object {
        private var instance: MyPreferences? = null
        private var sharedPreferences: SharedPreferences? = null

        fun getInstance(context: Context): MyPreferences {
            if (instance == null) {
                instance = MyPreferences()
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            }
            return instance!!
        }
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun saveFloat(key: String, value: Float) {
        val editor = sharedPreferences!!.edit()
        editor.putFloat(key, value)
        editor.commit()
    }

    fun saveBoolean(key: String, value: Boolean?) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun getString(key: String, defaulValue: String): String? {
        return sharedPreferences!!.getString(key, defaulValue)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences!!.getInt(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences!!.getFloat(key, defaultValue)
    }

    fun removeData(key: String) {
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        editor.apply()
        editor.commit()
    }

    fun removeAllData() {
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
        editor.commit()
    }
}