package com.example.app44.sharepref

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
    }

    // Lưu token vào SharedPreferences
    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    // Lấy token từ SharedPreferences
    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    // Xóa token
    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}
