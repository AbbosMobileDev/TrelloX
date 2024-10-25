package com.abisoft.trellox.model.repository

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Serializable
data class LoginResponse(
    val token: String,
    val user: User
)
@Serializable
data class User(
    @SerializedName("user_id")
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("avatar")
    val avatar: String
)
