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
        private const val a_token = "a_token"
        private const val a_thread = "a_thread"
        private const val a_shown = "a_shown"
        private const val a_id_course = "a_id_course"
        private const val a_month = "a_month"
        private const val a_year = "a_year"



        fun deleteAll() {
            Hawk.delete(a_login_data)
            Hawk.delete(a_first_time)
            Hawk.delete(a_login)
            Hawk.delete(a_token)
            Hawk.delete(a_thread)
            Hawk.delete(a_shown)
            Hawk.delete(a_id_course)
            Hawk.delete(a_month)
            Hawk.delete(a_year)

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
        fun putShown(value: Boolean) {
            Hawk.put(a_shown, value)
        }

        fun isShown(): Boolean {
            return (Hawk.get(a_shown, false))
        }
        fun putIdCourse(value: Int) {
            Hawk.put(a_id_course, value)
        }

        fun getIdCourse(): Int {
            return (Hawk.get(a_id_course, 0))
        }
        fun putMonth(value: Int) {
            Hawk.put(a_month, value)
        }

        fun getMonth(): Int {
            return (Hawk.get(a_month, 0))
        }
        fun putYear(value: Int) {
            Hawk.put(a_year, value)
        }

        fun getYear(): Int {
            return (Hawk.get(a_year, 0))
        }
    }
}