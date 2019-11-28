package com.android.kotlinmvvm.utils

import android.content.Context
import com.android.kotlinmvvm.data.preferences.MyPreferences
import javax.inject.Inject

class Session @Inject constructor(var context:Context) {

    fun isLoggedIn(): Boolean {
        return MyPreferences.getInstance(context).getBoolean(SHARED_PREF_LOGGED_IN, false)
    }

    fun setLoggedIn(/*user profile object */) : Boolean {
        return false
    }
}