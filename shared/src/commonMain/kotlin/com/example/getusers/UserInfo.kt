package com.example.getusers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val url: String,
    val type: String
)