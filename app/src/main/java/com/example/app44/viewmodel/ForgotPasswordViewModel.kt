package com.example.app44.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.request.ForgotPasswordRequest
import com.example.app44.domain.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ForgotPasswordUiState(
    val username: String = "",
    val email: String = "",
    val newPassword: String = "",
    val successMessage: String? = null,
    val errorMessage: String? = null
)


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onNewPasswordChange(value: String) {
        _uiState.update { it.copy(newPassword = value) }
    }

    fun submitForgotPassword() {
        viewModelScope.launch {
            val forgotPasswordRequest = ForgotPasswordRequest(
                username = _uiState.value.username,
                email = _uiState.value.email,
                newPassword = _uiState.value.newPassword
            )
            val response = forgotPasswordUseCase.invoke(
                forgotPasswordRequest
            )
            if (response is BaseResult.Success) {
                _uiState.update {
                    it.copy(
                        successMessage = "Yêu cầu thành công!",
                        errorMessage = null
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        successMessage = null,
                        errorMessage = "Yêu cầu thất bại!"
                    )
                }
            }
        }
    }
}
