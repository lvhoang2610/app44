package com.example.app44.viewmodel

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.response.InvoiceDto
import com.example.app44.domain.PrintInvoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PrintInvoiceViewModel @Inject constructor(
    private val listInvoicesUseCase: PrintInvoiceUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _listInvoice = MutableStateFlow<List<InvoiceDto>>(emptyList())
    val listInvoice: StateFlow<List<InvoiceDto>> = _listInvoice

    private var currentPage = 1
    private val pageSize = 10
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var endReached = false

    init {
        loadDocuments()
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



