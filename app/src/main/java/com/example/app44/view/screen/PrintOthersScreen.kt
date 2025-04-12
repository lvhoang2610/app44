package com.example.app44.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app44.core.PDFDownloader
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.enums.PrintLogType
import com.example.app44.navigation.Screen
import com.example.app44.view.component.InvoiceItem
import com.example.app44.viewmodel.PrintOtherDocumentViewModel

@Composable
fun PrintOthersScreen(
    navController: androidx.navigation.NavHostController
) {
    val viewModel: PrintOtherDocumentViewModel = hiltViewModel()
    val listOtherDoc by viewModel.listOtherDoc.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 1) {
                    viewModel.loadOtherDocs()
                }
            }
    }

    LazyColumn(
        modifier = Modifier.padding(20.dp),
        state = listState
    ) {
        items(listOtherDoc) { otherDoc ->
            InvoiceItem(
                tile = otherDoc.documentNumber,
                releaseDate = otherDoc.issueDate,
                filePath = otherDoc.filePath,
                onClickPreview = {
                    navController.navigate(Screen.PreviewPDF.createRoute(otherDoc.filePath))
                },
                onClickDownLoad = {
                    viewModel.printLog(
                        PrintLogRequest(
                            objectId = "${otherDoc.id}",
                            type = PrintLogType.INVOICE.type,
                        )
                    )
                    PDFDownloader.downloadPdf(
                        context,
                        otherDoc.filePath,
                        otherDoc.documentNumber,
                    )
                },
            )
        }
    }
}