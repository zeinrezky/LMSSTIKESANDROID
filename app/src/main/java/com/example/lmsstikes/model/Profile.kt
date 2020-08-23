package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Profile (
    @SerializedName("username")
    var username: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("img")
    var img: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("role")
    var role: String,
    @SerializedName("gpa")
    var gpa: String,
    @SerializedName("token")
    var token: String
)