package com.example.lmsstikes.util

import com.example.lmsstikes.model.Profile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.hawk.Hawk

class AppPreference {

    companion object {
        private const val a_login_data = "a_login_data"
        private const val a_first_time = "a_first_time"
        private const val a_login = "a_login"

        fun deleteAll() {
            Hawk.delete(a_login_data)
            Hawk.delete(a_first_time)
            Hawk.delete(a_login)
        }

        fun putLoginData(value: Profile) {
            Hawk.put(a_login_data, Gson().toJson(value))
        }

        fun getLoginData(): Profile {
            val type = object : TypeToken<Profile>() {}.type
            return Gson().fromJson<Profile>(Hawk.get(a_login_data, ""), type)
        }

        fun putFirstTime(value: Boolean) {
            Hawk.put(a_first_time, value)
        }

        fun isFirstTime(): Boolean {
            return (Hawk.get(a_first_time, true))
        }

        fun putLogin(value: Boolean) {
            Hawk.put(a_login, value)
        }

        fun isLogin(): Boolean {
            return (Hawk.get(a_login, false))
        }
    }
}