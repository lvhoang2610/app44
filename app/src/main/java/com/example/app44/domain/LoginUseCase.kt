package com.example.app44.domain

import com.example.app44.core.BaseResult
import com.example.app44.data.dto.request.LoginRequest
import com.example.app44.data.repository.DocumentApi
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val documentApi: DocumentApi
) {
    suspend operator fun invoke(loginRequest: LoginRequest): BaseResult<String> {
        return try {
            val token = documentApi.login(loginRequest)
            BaseResult.Success(token.accessToken)
        } catch (e: Exception) {
            BaseResult.Error(e.message.orEmpty())
        }
    }
}
