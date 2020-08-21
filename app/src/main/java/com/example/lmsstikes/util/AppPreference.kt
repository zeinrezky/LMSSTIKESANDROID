package com.example.lmsstikes.util

import com.example.lmsstikes.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.hawk.Hawk

class AppPreference {

    companion object {
        private const val a_login_data = "a_login_data"
        private const val a_first_time = "a_first_time"
        private const val a_login = "a_login"
        private const val a_token = "a_token"
        private const val a_language = "a_language"
        private const val a_user_id = "a_user_id"
        private const val a_customer_name = "a_customer_name"
        private const val a_phone = "a_phone"
        private const val a_email = "a_email"
        private const val a_profile_image_uri = "a_profile_image_uri"
        private const val a_profile_image = "a_profile_image"
        private const val a_device_token = "a_device_token"

        fun deleteAll() {
            Hawk.delete(a_login_data)
            Hawk.delete(a_first_time)
            Hawk.delete(a_login)
            Hawk.delete(a_token)
            Hawk.delete(a_language)
            Hawk.delete(a_user_id)
            Hawk.delete(a_customer_name)
            Hawk.delete(a_phone)
            Hawk.delete(a_email)
            Hawk.delete(a_profile_image_uri)
            Hawk.delete(a_profile_image)
            Hawk.delete(a_device_token)
        }

        fun putLoginData(value: User) {
            Hawk.put(a_login_data, Gson().toJson(value))
        }

        fun getLoginData(): User {
            val type = object : TypeToken<User>() {}.type
            return Gson().fromJson<User>(Hawk.get(a_login_data, ""), type)
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

        fun putLanguage(value: String) {
            Hawk.put(a_language, value)
        }

        fun getLanguage(): String {
            return (Hawk.get(a_language, "en"))
        }

        fun putUserId(value: Int) {
            Hawk.put(a_user_id, value)
        }

        fun getUserId(): Int {
            return (Hawk.get(a_user_id, 0))
        }

        fun putCustomerName(value: String) {
            Hawk.put(a_customer_name, value)
        }

        fun getCustomerName(): String {
            return (Hawk.get(a_customer_name, ""))
        }

        fun putPhone(value: String) {
            Hawk.put(a_phone, value)
        }

        fun getPhone(): String {
            return (Hawk.get(a_phone, ""))
        }

        fun putEmail(value: String) {
            Hawk.put(a_email, value)
        }

        fun getEmail(): String {
            return (Hawk.get(a_email, ""))
        }

        fun putProfileImageUri(value: String?) {
            Hawk.put(a_profile_image_uri, Gson().toJson(value))
        }

        fun getProfileImageUri(): String? {
            val type = object : TypeToken<String>() {}.type
            return Gson().fromJson<String>(Hawk.get(a_profile_image_uri, ""), type)
        }

        fun putProfileImage(value: String) {
            Hawk.put(a_profile_image, value)
        }

        fun getProfileImage(): String {
            return (Hawk.get(a_profile_image, ""))
        }

        fun putDeviceToken(value: String) {
            Hawk.put(a_device_token, value)
        }

        fun getDeviceToken(): String {
            return (Hawk.get(a_device_token, ""))
        }
    }
}