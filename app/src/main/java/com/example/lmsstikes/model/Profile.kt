package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Profile (
    @SerializedName("username")
    var username: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("img")
    var img: String,
    @SerializedName("id_user")
    var id_user: Int,
    @SerializedName("user_code")
    var user_code: String,
    @SerializedName("role")
    var role: String,
    @SerializedName("gpa")
    var gpa: String,
    @SerializedName("token")
    var token: String
)