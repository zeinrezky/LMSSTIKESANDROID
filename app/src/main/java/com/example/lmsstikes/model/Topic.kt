package com.example.lmsstikes.model

import com.google.gson.annotations.SerializedName

data class Topic (
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_session")
    val id_session: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("attachment")
    val attachment: String,
    @SerializedName("link")
    val link: String
)