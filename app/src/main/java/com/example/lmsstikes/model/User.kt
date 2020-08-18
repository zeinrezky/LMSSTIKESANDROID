package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("pm_user_id")
    val pm_user_id: Int,
    @SerializedName("pl_user_id")
    val pl_user_id: Int,
    @SerializedName("social_id")
    val social_id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("cell_phone")
    val cell_phone: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("current_latitude")
    val current_latitude: String,
    @SerializedName("current_longitude")
    val current_longitude: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_type")
    val rating_type: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("login_type")
    val login_type: String,
    @SerializedName("last_login")
    val last_login: String,
    @SerializedName("forgotpwd_date")
    val forgotpwd_date: String,
    @SerializedName("forgotpwd_token")
    val forgotpwd_token: String,
    @SerializedName("is_active")
    val is_active: Int,
    @SerializedName("is_verify")
    val is_verify: Int,
    @SerializedName("insertdate")
    val insertdate: String,
    @SerializedName("updatetime")
    val updatetime: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("pointUpdateAt")
    val pointUpdateAt: String,
    @SerializedName("is_deleted")
    val is_deleted: Int,
    @SerializedName("is_flage")
    val is_flage: Int,
    @SerializedName("approvedate")
    val approvedate: String,
    @SerializedName("image_name")
    val image_name: String,
    @SerializedName("token")
    val token: String
)