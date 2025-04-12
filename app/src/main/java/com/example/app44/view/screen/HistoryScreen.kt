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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.app44.view.component.PrintLogItem
import com.example.app44.viewmodel.PrintHistoryViewModel

@Composable
fun HistoryScreen(navHostController: NavHostController) {
    val viewModel: PrintHistoryViewModel = hiltViewModel()
    val printLogs by viewModel.listPrintLog.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.loadPrintLogs()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 1) {
                    viewModel.loadPrintLogs()
                }
            }
    }

    LazyColumn(
        state = listState
    ) {
        items(printLogs) { printLog ->
            PrintLogItem(printLog)
        }
    }
}