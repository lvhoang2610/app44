package com.example.app44.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.data.dto.response.InvoiceResponse
import com.example.app44.domain.PrintInvoiceUseCase
import com.example.app44.domain.PrintLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PrintInvoiceViewModel @Inject constructor(
    private val listInvoicesUseCase: PrintInvoiceUseCase,
    private val printLogUseCase: PrintLogUseCase
) : ViewModel() {

    private val _listInvoice = MutableStateFlow<List<InvoiceResponse>>(emptyList())
    val listInvoice: StateFlow<List<InvoiceResponse>> = _listInvoice

    private var currentPage = 1
    private val pageSize = 10
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var endReached = false


    fun printLog(printLogRequest: PrintLogRequest) {
        viewModelScope.launch {
            when (val result = printLogUseCase.execute(printLogRequest)) {
                is BaseResult.Success -> {
                    Log.d("PrintLog", "Print log successful: ${result.data}")
                }

                is BaseResult.Error -> {
                    // handle error
                }
            }
        }
    }

    fun loadDocuments() {
        if (_isLoading.value || endReached) return
        viewModelScope.launch {
            _isLoading.value = true

            val result = listInvoicesUseCase.execute(
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
                        _listInvoice.update { it + newItems }
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



