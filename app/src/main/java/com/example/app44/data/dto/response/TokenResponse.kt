package com.example.app44.data.dto.response

import com.squareup.moshi.Json


data class TokenResponse(
    @Json(name = "access_token") val accessToken: String,

    @Json(name = "refresh_token") val refreshToken: String,

    @Json(name = "token_type") val tokenType: String = "bearer",

    @Json(name = "expires_in") val expiresIn: Int,

    @Json(name = "user") val user: Any
)