package com.example.app44.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.app44.core.PDFDownloader
import com.example.app44.navigation.Screen
import com.example.app44.view.component.InvoiceItem
import com.example.app44.viewmodel.PrintInvoiceViewModel

@Composable
fun PrintInvoiceScreen(navHostController: NavHostController) {
    val viewModel: PrintInvoiceViewModel = hiltViewModel()
    val invoices by viewModel.listInvoice.collectAsState()
    val loadingState by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 1) {
                    viewModel.loadDocuments()
                }
            }
    }

    LazyColumn(
        modifier = Modifier.padding(20.dp),
        state = listState
    ) {
        items(invoices) { invoice ->
            InvoiceItem(invoice,
                onClickPreview = {
                    navHostController.navigate(Screen.PreviewPDF.createRoute(invoice.filePath))
                },
                onClickDownLoad = {
                    PDFDownloader.downloadPdf(
                        context,
                        invoice.filePath,
                        invoice.invoiceNumber,
                    )
                }
            )
        }
        item {
            if (loadingState) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}