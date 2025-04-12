package com.example.app44.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app44.core.BaseResult
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.data.dto.response.ContractResponse
import com.example.app44.domain.PrintContractUseCase
import com.example.app44.domain.PrintLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrintContractViewModel @Inject constructor(
    private val printContractUseCase: PrintContractUseCase,
    private val printLogUseCase: PrintLogUseCase
) : ViewModel() {

    private val _listContract = MutableStateFlow<List<ContractResponse>>(emptyList())
    val listContract: StateFlow<List<ContractResponse>> = _listContract

    private var currentPage = 1
    private val pageSize = 10
    private var _isLoading = MutableStateFlow(false)
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

    fun loadContracts() {
        if (_isLoading.value || endReached) return
        viewModelScope.launch {
            _isLoading.value = true

            val result = printContractUseCase.execute(
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
                        _listContract.update { it + newItems }
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
