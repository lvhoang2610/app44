package com.example.app44.domain

import com.example.app44.core.BaseResult
import com.example.app44.data.api.DocumentApi
import com.example.app44.data.dto.request.ForgotPasswordRequest
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val documentApi: DocumentApi
) {
    suspend operator fun invoke(forgotPasswordRequest: ForgotPasswordRequest): BaseResult<String> {
        return try {
            val data = documentApi.resetPassword(forgotPasswordRequest)
            BaseResult.Success(data.message)
        } catch (e: Exception) {
            BaseResult.Error(e.message.orEmpty())
        }
    }
}

enum class RESET_PASSWORD_STATUS(val message: String) {
    SUCCESS("success"),
}
