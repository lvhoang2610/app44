package com.example.app44.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.enums.PrintLogType
import com.example.app44.navigation.Screen
import com.example.app44.view.component.InvoiceItem
import com.example.app44.viewmodel.PrintContractViewModel
import com.example.app44.viewmodel.PrintDeliveryNoteViewModel

@Composable
fun PrintDeliveryScreen(navHostController: NavHostController) {
    val viewModel: PrintDeliveryNoteViewModel = hiltViewModel()
    val listContract by viewModel.listDeliveryNotes.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 1) {
                    viewModel.loadDeliveryNotes()
                }
            }
    }


    LazyColumn(
        state = listState
    ) {
        items(listContract) { contract ->
            InvoiceItem(
                title = contract.deliveryNumber,
                releaseDate = contract.issueDate,
                filePath = contract.filePath,
                onClickPreview = {
                    navHostController.navigate(Screen.PreviewPDF.createRoute(contract.filePath))
                },
                onClickDownLoad = {
                    viewModel.printLog(
                        PrintLogRequest(
                            objectId = "${contract.id}",
                            type = PrintLogType.CONTRACT.type,
                        )
                    )
                    PDFDownloader.downloadPdf(
                        context,
                        contract.filePath,
                        contract.deliveryNumber,
                    )
                },
            )
        }
    }
}