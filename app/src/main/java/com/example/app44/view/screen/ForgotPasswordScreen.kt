package com.example.app44.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.app44.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController, viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quên mật khẩu", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = uiState.username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text("Tên đăng nhập") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.newPassword,
            onValueChange = { viewModel.onNewPasswordChange(it) },
            label = { Text("Mật khẩu mới") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Outlined.AccountBox else Icons.Outlined.AccountCircle
                val description = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.submitForgotPassword() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gửi yêu cầu đặt lại mật khẩu")
        }

        if (uiState.successMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(uiState.successMessage!!, color = Color.Green)
        }

        if (uiState.errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(uiState.errorMessage!!, color = Color.Red)
        }
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Quay lại màn đăng nhập", textAlign = TextAlign.Center)
        }
    }
}
