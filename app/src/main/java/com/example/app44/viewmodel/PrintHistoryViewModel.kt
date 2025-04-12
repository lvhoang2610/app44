package com.example.app44.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.response.PrintLogResponse
import com.example.app44.domain.PrintLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrintHistoryViewModel @Inject constructor(
    private val printLogUseCase: PrintLogUseCase
) : ViewModel() {

    private val _listPrintLog = MutableStateFlow<List<PrintLogResponse>>(emptyList())
    val listPrintLog: StateFlow<List<PrintLogResponse>> = _listPrintLog

    private var currentPage = 1
    private val pageSize = 10
    private var _isLoading = MutableStateFlow(false)
    private var endReached = false

    fun loadPrintLogs() {
        if (_isLoading.value || endReached) return
        viewModelScope.launch {
            _isLoading.value = true

            val result = printLogUseCase.getListPrintLog(
                page = currentPage,
                pageSize = pageSize,
                fromDate = null,
                toDate = null
            )

            when (result) {
                is BaseResult.Success -> {
                    val newItems = result.data.items
                    if (newItems.isEmpty()) {
                        endReached = true
                    } else {
                        _listPrintLog.update { it + newItems }
                        currentPage++
                    }
                }

                is BaseResult.Error -> {
                    // handle error
                }
            }
            _isLoading.value = false
        }
    }
}