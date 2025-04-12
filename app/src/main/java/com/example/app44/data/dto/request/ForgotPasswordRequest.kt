package com.example.app44.data.dto.request

data class ForgotPasswordRequest(
    val username: String,
    val email: String,
    val newPassword: String
)