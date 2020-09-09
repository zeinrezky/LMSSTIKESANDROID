package com.example.lmsstikes.util

import com.example.lmsstikes.model.Profile
import com.example.lmsstikes.model.Thread
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.hawk.Hawk

class AppPreference {

    companion object {
        private const val a_login_data = "a_login_data"
        private const val a_first_time = "a_first_time"
        private const val a_login = "a_login"
        private const val a_token = "a_login"
        private const val a_thread = "a_thread"

        fun deleteAll() {
            Hawk.delete(a_login_data)
            Hawk.delete(a_first_time)
            Hawk.delete(a_login)
            Hawk.delete(a_token)
            Hawk.delete(a_thread)
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

        fun putToken(value: String) {
            Hawk.put(a_token, value)
        }

        fun getToken(): String {
            return (Hawk.get(a_token, ""))
        }

        fun putThreadData(value: Thread.ThreadList) {
            Hawk.put(a_thread, Gson().toJson(value))
        }

        fun getThreadData(): Thread.ThreadList {
            val type = object : TypeToken<Thread.ThreadList>() {}.type
            return Gson().fromJson<Thread.ThreadList>(Hawk.get(a_thread, ""), type)
        }
    }
}