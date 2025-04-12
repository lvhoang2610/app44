package com.example.app44.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: ImageVector = Icons.Default.Home
) {
    object Login : Screen("login")
    object ForgotPassword : Screen("forgot_password")
    object MainScreen : Screen("main_screen")

    object PrintInvoice : Screen("print_invoice", "Hóa đơn", Icons.Default.Home)
    object PrintDelivery : Screen("print_delivery", "Xuất kho", Icons.Default.Info)
    object PrintBill : Screen("print_bill", "Vận đơn", Icons.Default.AccountBox)
    object PrintContract : Screen("print_contract", "HĐ điện tử", Icons.Default.ShoppingCart)
    object PrintOthers : Screen("print_others", "Chứng từ khác", Icons.Default.Settings)
    object History : Screen("history", "Lịch sử In", Icons.Default.Build)

    object PreviewPDF :
        Screen("preview_pdf?pdfUrl={pdfUrl}", "Xem trước PDF", Icons.Default.Build) {
        fun createRoute(pdfUrl: String): String {
            return "preview_pdf?pdfUrl=${Uri.encode(pdfUrl)}"
        }
    }

}

