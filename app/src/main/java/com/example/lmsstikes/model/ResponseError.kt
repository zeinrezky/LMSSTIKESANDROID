package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class ResponseError (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)