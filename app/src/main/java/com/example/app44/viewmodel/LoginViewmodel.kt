package com.example.app44.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.request.LoginRequest
import com.example.app44.domain.LoginUseCase
import com.example.app44.sharepref.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, private val tokenManager: TokenManager

) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun login() {
        val loginRequest = LoginRequest(
            username = uiState.email, password = uiState.password
        )
        viewModelScope.launch {
            val result = loginUseCase.invoke(loginRequest)
            if (result is BaseResult.Success) {
                uiState = uiState.copy(isLoginSuccess = true, isLoading = false)
                tokenManager.saveToken(result.data)
            } else {
                uiState = uiState.copy(
                )
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }


}
