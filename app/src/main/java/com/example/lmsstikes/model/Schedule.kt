package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Schedule (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("status")
    val status: String
)